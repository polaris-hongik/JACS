package com.selab.jacs;

import org.json.simple.JSONObject;

public class ForStatement extends Statement {
	private Block body;
	private VariableDeclarationStatement initializationExpression;
	private ExpressionStatement loopExpression;
	private Value condition;

	public ForStatement(JSONObject node) {
		super(node);
		JSONObject condition = (JSONObject) node.get("condition");
		if(condition.get("nodeType").equals("BinaryOperation")) {
			this.condition = new BinaryOperation(condition);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(condition.get("nodeType"));
			System.out.println("At id:"+condition.get("id"));
		}
		this.body = new Block((JSONObject) node.get("body"));
		this.loopExpression = new ExpressionStatement((JSONObject) node.get("loopExpression"));
		this.initializationExpression = new VariableDeclarationStatement((JSONObject) node.get("initializationExpression"));
	}
	public Block getBody() {
		return body;
	}
	public VariableDeclarationStatement getInitializationExpression() {
		return initializationExpression;
	}
	public ExpressionStatement getLoopExpression() {
		return loopExpression;
	}
	public Value getCondition() {
		return condition;
	}
}
