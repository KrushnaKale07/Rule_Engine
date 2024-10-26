package com.rule.engine.model;

import java.util.Map;

public class EvaluateRuleRequest {

	private String ruleAstJson; // The AST JSON representation of the rule
	private Map<String, Object> data; // User attributes for evaluation

	public EvaluateRuleRequest() {
	}

	public EvaluateRuleRequest(String ruleAstJson, Map<String, Object> data) {
		this.ruleAstJson = ruleAstJson;
		this.data = data;
	}

	public String getRuleAstJson() {
		return ruleAstJson;
	}

	public void setRuleAstJson(String ruleAstJson) {
		this.ruleAstJson = ruleAstJson;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
