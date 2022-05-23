package com.selab.jacs;

import org.json.simple.JSONObject;

public class ExpressionStatement extends Statement {
	private FunctionCall eventCall;
	private Value expression;
	
	public ExpressionStatement(JSONObject node) {
		super(node);
		//FunctionCall 만 있는지 확실치 않음
		JSONObject eventcall = (JSONObject)node.get("eventCall");
		if(eventcall != null) {
			if(eventcall.get("nodeType").equals("FunctionCall")) {
				this.eventCall = new FunctionCall(eventcall);
			}else {
				System.out.println("=== Find new eventCallType ===");
				System.out.println(eventcall.get("nodeType"));
				System.out.println("At id:"+eventcall.get("id"));
				System.out.println("At Parent NodeType:"+this.getNodeType());
			}
		}
		JSONObject expression = (JSONObject)node.get("expression");
		if(expression != null) {
			if(expression.get("nodeType").equals("FunctionCall")) {
				this.expression = new FunctionCall(expression);
			}else if(expression.get("nodeType").equals("UnaryOperation")) {
				this.expression = new UnaryOperation(expression);
			}else if(expression.get("nodeType").equals("Assignment")) {
				this.expression = new Assignment(expression);
			}else {
				System.out.println("=== Find new eventCallType ===");
				System.out.println(expression.get("nodeType"));
				System.out.println("At id:"+expression.get("id"));
				System.out.println("At Parent NodeType:"+this.getNodeType());
			}
		}
	}
	public FunctionCall getEventCall() {
		return eventCall;
	}
	public Value getExpression() {
		return expression;
	}
}
