package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class Value extends Expression{
	public Value(JSONObject jsonObj) {
		super(jsonObj);
	}
	
	protected static Value getValue(JSONObject jsonObj) {
		Value typedValue = null;
		
		if(jsonObj.get("nodeType").equals("Literal")) {
			typedValue = new Literal(jsonObj);
		}else if(jsonObj.get("nodeType").equals("Identifier")) {
			typedValue = new Identifier((JSONObject) jsonObj);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(jsonObj.get("nodeType"));
			System.out.println("At id: "+jsonObj.get("id"));
		}
		return typedValue;
	}
	
	protected static boolean hasValue(JSONObject jsonObj) {
		boolean exist = false;
		
		if(jsonObj.get("nodeType").equals("Literal")) {
			exist = true;
		}else if(jsonObj.get("nodeType").equals("Identifier")) {
			exist = true;
		}
		return exist;
	}
}
