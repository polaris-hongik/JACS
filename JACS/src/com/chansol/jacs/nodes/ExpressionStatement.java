package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class ExpressionStatement extends Statement {
	private Expression eventCall;
	private Expression expression;
	
	public ExpressionStatement(JSONObject jsonObj) {
		super(jsonObj);
		if(jsonObj.get("eventCall")!=null) {
			this.eventCall = Expression.getExpression((JSONObject)jsonObj.get("eventCall"));
		}
		this.expression = Expression.getExpression((JSONObject)jsonObj.get("expression"));
	}
	
	//getter
	public Expression getEventCall() { return eventCall; }
	public Expression getExpression() { return expression; }
}
