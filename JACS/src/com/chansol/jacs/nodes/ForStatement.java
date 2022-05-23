package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class ForStatement extends Statement {
	private Block body;
	private Statement initializationExpression;
	private ExpressionStatement loopExpression;
	private Operation condition;

	public ForStatement(JSONObject jsonObj) {
		super(jsonObj);
		this.condition = Operation.getOperation((JSONObject) jsonObj.get("condition"));
		this.body = new Block((JSONObject) jsonObj.get("body"));
		this.loopExpression = new ExpressionStatement((JSONObject) jsonObj.get("loopExpression"));
		this.initializationExpression = Statement.getStatement((JSONObject) jsonObj.get("initializationExpression"));
	}
	
	//getter
	public Block getBody() { return body; }
	public Statement getInitializationExpression() { return initializationExpression; }
	public ExpressionStatement getLoopExpression() { return loopExpression; }
	public Operation getCondition() { return condition; }
}
