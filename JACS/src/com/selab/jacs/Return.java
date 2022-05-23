package com.selab.jacs;

import org.json.simple.JSONObject;

public class Return extends Statement {
	private Value expression;
	private Long functionReturnParameters;
	
	public Return(JSONObject node) {
		super(node);
		this.functionReturnParameters = (Long) node.get("functionReturnParameters");
		JSONObject expression = (JSONObject) node.get("expression");
		if(expression != null) {
			if(expression.get("nodeType").equals("Literal")) {
				this.expression = new Literal(expression);
			}else if(expression.get("nodeType").equals("Identifier")) {
				this.expression = new Identifier(expression);
			}else if(expression.get("nodeType").equals("MemberAccess")) {
				this.expression = new MemberAccess(expression);
			}else {
				System.out.println("=== Find new Value ===");
				System.out.println(expression.get("nodeType"));
				System.out.println("At id:"+expression.get("id"));
				System.out.println("At Parent NodeType:"+this.getNodeType());
			}
		}
	}

	public Value getExpression() {
		return expression;
	}

	public Long getFunctionReturnParameters() {
		return functionReturnParameters;
	}

}
