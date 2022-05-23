package com.selab.jacs;

import org.json.simple.JSONObject;

public class IfStatement extends Statement{
	private Value condition;
	private Block trueBody;
	private Block falseBody;
	
	public IfStatement(JSONObject node) {
		super(node);
		this.trueBody = new Block((JSONObject) node.get("trueBody"));
		JSONObject falseBody = (JSONObject) node.get("falseBody");
		if(falseBody != null) {
			this.falseBody = new Block(falseBody);
		}
		JSONObject condition = (JSONObject) node.get("condition");
		if(condition.get("nodeType").equals("BinaryOperation")) {
			this.condition = new BinaryOperation(condition);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(condition.get("nodeType"));
			System.out.println("At id:"+condition.get("id"));
		}
	}
	public Value getCondition() {
		return condition;
	}
	public Block getTrueBody() {
		return trueBody;
	}
	public Block getFalseBody() {
		return falseBody;
	}
	
}
