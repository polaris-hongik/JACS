package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class EmitStatement extends Statement{
	private Expression eventCall;

	public EmitStatement(JSONObject jsonObj) {
		super(jsonObj);
		this.eventCall = Expression.getExpression((JSONObject)jsonObj.get("eventCall"));
	}
	
	//getter
	public Expression getEventCall() { return eventCall; }
}
