package com.selab.jacs;

import org.json.simple.JSONObject;

public class IndexAccess extends Value {
    private Value indexExpression;
	private Identifier baseExpression;
	
	public IndexAccess(JSONObject node) {
		super(node);
		JSONObject indexExpression = (JSONObject) node.get("indexExpression");
		if(indexExpression.get("nodeType").equals("MemberAccess")) {
			this.indexExpression =new MemberAccess(indexExpression);
		}else if(indexExpression.get("nodeType").equals("Identifier")) {
			this.indexExpression =new Identifier(indexExpression);
		}else if(indexExpression.get("nodeType").equals("IndexAccess")) {
			this.indexExpression =new IndexAccess(indexExpression);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(indexExpression.get("nodeType"));
			System.out.println("At id:"+indexExpression.get("id"));
		}
		
		this.baseExpression = new Identifier((JSONObject) node.get("baseExpression")) ;
	}

	public Value getIndexExpression() {
		return indexExpression;
	}

	public Identifier getBaseExpression() {
		return baseExpression;
	}
}
