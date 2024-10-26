package com.rule.engine.model;

public class ASTNode {

	private String type;
	private ASTNode left;
	private ASTNode right;
	private String value;

	public ASTNode() {
		super();
	}

	public ASTNode(String type, String value) {
		this.type = type;
		this.value = value;
	}

	public ASTNode(String string, String string2, ASTNode combined, ASTNode newRule) {
		super();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ASTNode getLeft() {
		return left;
	}

	public void setLeft(ASTNode left) {
		this.left = left;
	}

	public ASTNode getRight() {
		return right;
	}

	public void setRight(ASTNode right) {
		this.right = right;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
