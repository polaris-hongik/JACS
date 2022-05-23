package com.selab.jacs;

import org.json.simple.JSONObject;

public class BinaryOperation extends Operation{
	private TypeDescriptions commonType;
	private Value leftExpression;
	private Value rightExpression; 
	private Boolean prefix;
	
	public BinaryOperation(JSONObject node) {
		super(node);
		this.prefix = (Boolean) node.get("prefix");
		JSONObject leftExpression = (JSONObject) node.get("leftExpression");
		if(leftExpression.get("nodeType").equals("Literal")) {
			this.leftExpression = new Literal(leftExpression);
		}else if(leftExpression.get("nodeType").equals("UnaryOperation")) {
			this.leftExpression = new UnaryOperation(leftExpression);
		}else if(leftExpression.get("nodeType").equals("BinaryOperation")) {
			this.leftExpression = new BinaryOperation(leftExpression);
		}else if(leftExpression.get("nodeType").equals("Identifier")) {
			this.leftExpression = new Identifier(leftExpression);
		}else if(leftExpression.get("nodeType").equals("MemberAccess")) {
			this.leftExpression = new MemberAccess(leftExpression);
		}else if(leftExpression.get("nodeType").equals("FunctionCall")) {
			this.leftExpression = new FunctionCall(leftExpression);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(leftExpression.get("nodeType"));
			System.out.println("At id:"+leftExpression.get("id"));
			System.out.println("At Parent NodeType:"+this.getNodeType());
		}

		JSONObject rightExpression = (JSONObject) node.get("rightExpression");
		if(rightExpression.get("nodeType").equals("Literal")) {
			this.rightExpression = new Literal(rightExpression);
		}else if(rightExpression.get("nodeType").equals("UnaryOperation")) {
			this.rightExpression = new UnaryOperation(rightExpression);
		}else if(rightExpression.get("nodeType").equals("BinaryOperation")) {
			this.rightExpression = new BinaryOperation(rightExpression);
		}else if(rightExpression.get("nodeType").equals("Identifier")) {
			this.rightExpression = new Identifier(rightExpression);
		}else if(rightExpression.get("nodeType").equals("MemberAccess")) {
			this.rightExpression = new MemberAccess(rightExpression);
		}else if(rightExpression.get("nodeType").equals("FunctionCall")) {
			this.rightExpression = new FunctionCall(rightExpression);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(rightExpression.get("nodeType"));
			System.out.println("At id:"+rightExpression.get("id"));
			System.out.println("At Parent NodeType:"+this.getNodeType());
		}
		this.commonType = new TypeDescriptions((JSONObject) node.get("commonType"));
	}
	public Boolean getPrefix() {
		return prefix;
	}
	
	public TypeDescriptions getCommonType() {
		return commonType;
	}

	public Value getLeftExpression() {
		return leftExpression;
	}

	public Value getRightExpression() {
		return rightExpression;
	}
}
