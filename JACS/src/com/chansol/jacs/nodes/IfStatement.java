package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class IfStatement extends Statement{
	private Expression condition;
	private Node trueBody;
	private Node falseBody;
	
	public IfStatement(JSONObject jsonObj) {
		super(jsonObj);
		JSONObject trueBody = (JSONObject) jsonObj.get("trueBody");
		if(trueBody.get("nodeType").equals("Block")) {
			this.trueBody = new Block(trueBody);
		}else if(trueBody.get("nodeType").equals("Return")) {
			this.trueBody = new Return(trueBody);
		}
		JSONObject falseBody = (JSONObject) jsonObj.get("falseBody");
		if(falseBody != null) {
			if(falseBody.get("nodeType").equals("Block")) {
				this.falseBody = new Block(falseBody);
			}else if(falseBody.get("nodeType").equals("IfStatement")) {
				this.falseBody = new IfStatement(falseBody);
			}
			
		}
		this.condition = Expression.getExpression((JSONObject) jsonObj.get("condition"));
	}
	
	//getter
	public Expression getCondition() { return condition; }
	public Node getTrueBody() { return trueBody; }
	public Node getFalseBody() { return falseBody; }
}
