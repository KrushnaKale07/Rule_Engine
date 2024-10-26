package com.rule.engine.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rule.engine.model.ASTNode;
import com.rule.engine.model.EvaluateRuleRequest;
import com.rule.engine.service.RuleService;

@RestController
@RequestMapping("/api/rules")
public class RuleController {

	@Autowired
	private RuleService ruleService;

	@PostMapping("/create")
	public ASTNode createRule(@RequestBody String ruleString) {
		return ruleService.createRule(ruleString);
	}

	@PostMapping("/combine")
	public ASTNode combineRules(@RequestBody String[] ruleStrings) {
		return ruleService.combineRules(ruleStrings);
	}

	@PostMapping("/evaluate")
	public boolean evaluateRule(@RequestBody EvaluateRuleRequest request) {
		return ruleService.evaluateRule(request.getRuleAstJson(), request.getData());
	}

	@PostMapping("/modify/{id}")
	public ASTNode modifyRule(@PathVariable Long id, @RequestBody String modifiedRuleString) {
		return ruleService.modifyRule(id, modifiedRuleString);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleInvalidInput(IllegalArgumentException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
