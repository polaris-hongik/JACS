package com.selab.jacs;

import org.json.simple.JSONObject;

public class UnaryOperation extends Operation{
	private Value subExpression;
	private Boolean prefix;

	public UnaryOperation(JSONObject node) {
		super(node);
		this.prefix = (Boolean) node.get("prefix");
		
		JSONObject subExpression = (JSONObject) node.get("subExpression");
		if(subExpression.get("nodeType").equals("Literal")) {
			this.subExpression = new Literal(subExpression);
		}else if(subExpression.get("nodeType").equals("Identifier")) {
			this.subExpression = new Identifier(subExpression);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(subExpression.get("nodeType"));
			System.out.println("At id:"+subExpression.get("id"));
		}
	}
	public Boolean getPrefix() {
		return prefix;
	}
	
	public Value getSubExpression() {
		return subExpression;
	}
	
}
