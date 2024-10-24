package com.rule.engine.service;

import org.springframework.stereotype.Service;

import com.rule.engine.model.ASTNode;

@Service
public class RuleService {

	public ASTNode createRule(String ruleString) {

		return parseRule(ruleString);
	}

	private ASTNode parseRule(String ruleString) {
		return new ASTNode("operand", ruleString);
	}

}
