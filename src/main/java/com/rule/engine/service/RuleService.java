package com.rule.engine.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rule.engine.model.ASTNode;
import com.rule.engine.model.Rule;
import com.rule.engine.repository.RuleRepository;

@Service
public class RuleService {

	@Autowired
	private RuleRepository ruleRepository;

	private final ObjectMapper objectMapper = new ObjectMapper();

	public ASTNode createRule(String ruleString) {

		if (ruleString == null || ruleString.isEmpty()) {
			throw new IllegalArgumentException("Rule string cannot be empty");
		}

		ASTNode ast = parseRule(ruleString);
		try {
			String astJson = objectMapper.writeValueAsString(ast);
			// Save to database
			Rule rule = new Rule(ruleString, astJson);
			ruleRepository.save(rule);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ast;
	}

	public ASTNode combineRules(String[] ruleStrings) {
		if (ruleStrings == null || ruleStrings.length == 0) {
			throw new IllegalArgumentException("Rule string cannot be empty");
		}

		ASTNode combined = new ASTNode("operator", "AND");
		for (String rule : ruleStrings) {
			ASTNode newRule = createRule(rule);
			combined = new ASTNode("operator", "AND", combined, newRule);
		}
		return combined;
	}

	public boolean evaluateRule(String ruleAstJson, Map<String, Object> data) {
		try {
			ASTNode root = objectMapper.readValue(ruleAstJson, ASTNode.class);
			return evaluateAST(root, data);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Rule getRuleById(Long id) {
		return ruleRepository.findById(id).orElse(null);
	}

	private ASTNode parseRule(String ruleString) {
		if (ruleString == null || ruleString.isEmpty()) {
			throw new IllegalArgumentException("Rule string cannot be empty");
		}
		return new ASTNode("operand", ruleString);
	}

	private boolean evaluateAST(ASTNode node, Map<String, Object> data) {
		if ("operator".equals(node.getType())) {
			boolean left = evaluateAST(node.getLeft(), data);
			boolean right = evaluateAST(node.getRight(), data);
			switch (node.getValue()) {
			case "AND":
				return left && right;
			case "OR":
				return left || right;
			}
		} else if ("operand".equals(node.getType())) {
			return evaluateCondition(node.getValue(), data);
		}
		return false;
	}

	private boolean evaluateCondition(String condition, Map<String, Object> data) {
		return true;
	}

	public ASTNode modifyRule(Long ruleId, String modifiedRuleString) {
		Rule rule = ruleRepository.findById(ruleId).orElse(null);
		if (rule != null) {
			// Parse and update the rule's AST
			ASTNode modifiedAst = parseRule(modifiedRuleString);
			try {
				String modifiedAstJson = objectMapper.writeValueAsString(modifiedAst);
				rule.setAst(modifiedAstJson);
				ruleRepository.save(rule); // Save updated rule to DB
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return modifiedAst;
		}
		return null;
	}

}
