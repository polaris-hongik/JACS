package com.selab.jacs;

import org.json.simple.JSONObject;

public class Operation extends Value{
	protected String operator;

	public Operation(JSONObject node) {
		super(node);
		this.operator = (String) node.get("operator");
	}

	public String getOperator() {
		return operator;
	}
}
