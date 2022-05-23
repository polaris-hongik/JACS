package com.selab.jacs;

import org.json.simple.JSONObject;

public class MemberAccess extends Value{
	private Value expression;
    private String memberName;
    
    public MemberAccess(JSONObject node) {
		super(node);
		JSONObject expression = (JSONObject)node.get("expression");
		if(expression.get("nodeType").equals("Identifier")) {
			this.expression = new Identifier((JSONObject)node.get("expression"));
		}else if(expression.get("nodeType").equals("FunctionCall")) {
			this.expression = new FunctionCall((JSONObject)node.get("expression"));
		}else if(expression.get("nodeType").equals("IndexAccess")) {
			this.expression = new IndexAccess((JSONObject)node.get("expression"));
		}else {
			System.out.println("=== Find new Expression ===");
			System.out.println(expression.get("nodeType"));
			System.out.println("At id:"+expression.get("id"));
		}
		this.memberName = (String)node.get("memberName");
	}

	public Value getExpression() {
		return expression;
	}
	public String getMemberName() {
		return memberName;
	}
    
}
