package com.rule.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rule.engine.model.ASTNode;
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
}
