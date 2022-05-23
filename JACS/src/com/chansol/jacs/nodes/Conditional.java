package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Conditional extends Expression {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private Expression condition;
	private Expression falseExpression;
	private Expression trueExpression;

	
	public Conditional(JSONObject jsonObj) {
		super(jsonObj);
		
		this.falseExpression = Expression.getExpression((JSONObject) jsonObj.get("falseExpression"));
		this.trueExpression = Expression.getExpression((JSONObject) jsonObj.get("trueExpression"));
		this.condition = Expression.getExpression((JSONObject) jsonObj.get("condition"));
		if(jsonObj.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
	}
	
	//getter
	public Vector<HashMap<String, String>> getArgumentTypes() { return argumentTypes; }
	public Expression getCondition() { return condition; }
	public Expression getFalseExpression() { return falseExpression; }
	public Expression getTrueExpression() {	return trueExpression; }
}
