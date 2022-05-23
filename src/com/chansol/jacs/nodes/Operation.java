package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class Operation extends Expression{
	protected String operator;

	public Operation(JSONObject jsonObj) {
		super(jsonObj);
		this.operator = (String) jsonObj.get("operator");
	}
	
	protected static Operation getOperation(JSONObject jsonObj) {
		Operation typedOperation = null;
		
		if(jsonObj.get("nodeType").equals("UnaryOperation")) {
			typedOperation = new UnaryOperation(jsonObj);
		}else if(jsonObj.get("nodeType").equals("BinaryOperation")) {
			typedOperation = new BinaryOperation(jsonObj);
		}else if(jsonObj.get("nodeType").equals("Assignment")) {
			typedOperation = new Assignment(jsonObj);
		}else {
			System.out.println("=== Find new Operation ===");
			System.out.println(jsonObj.get("nodeType"));
			System.out.println("At id: "+jsonObj.get("id"));
		}
		return typedOperation;
	}
	
	protected static boolean hasOperation(JSONObject jsonObj) {
		boolean exist = false;
		
		if(jsonObj.get("nodeType").equals("UnaryOperation")) {
			exist = true;
		}else if(jsonObj.get("nodeType").equals("BinaryOperation")) {
			exist = true;
		}else if(jsonObj.get("nodeType").equals("Assignment")) {
			exist = true;
		}
		return exist;
	}
	
	//getter
	public String getOperator() { return operator; }
}
