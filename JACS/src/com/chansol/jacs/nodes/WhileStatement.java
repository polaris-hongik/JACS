package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class WhileStatement extends Statement {
	private Operation condition;
	private Block body;

	public WhileStatement(JSONObject jsonObj) {
		super(jsonObj);
		
		this.body = new Block((JSONObject) jsonObj.get("body"));
		this.condition = Operation.getOperation((JSONObject) jsonObj.get("condition"));
	}
	
	//getter
	public Operation getCondition() { return condition; }
	public Block getBody() { return body; }
}
