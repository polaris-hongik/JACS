package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class Return extends Statement {
	private Expression expression;
	private Long functionReturnParameters;
	
	public Return(JSONObject jsonObj) {
		super(jsonObj);
		this.functionReturnParameters = (Long) jsonObj.get("functionReturnParameters");
		if(jsonObj.get("expression") != null) {
			this.expression = Expression.getExpression((JSONObject) jsonObj.get("expression"));
		}
	}

	//getter
	public Expression getExpression() { return expression; }
	public Long getFunctionReturnParameters() { return functionReturnParameters; }
}
