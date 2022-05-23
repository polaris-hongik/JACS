package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FunctionCall extends Expression{
	private Vector<Expression> arguments = new Vector<Expression>();
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private Expression expression;
    private String kind;
    private Vector<String> names = new Vector<String>(); // ÀÌ°Ô ¹¹Áö
    private boolean tryCall;


	public FunctionCall(JSONObject jsonObj) {
		super(jsonObj);
		this.kind = (String)jsonObj.get("kind");
		this.tryCall = (boolean)jsonObj.get("tryCall");
		this.expression = Expression.getExpression((JSONObject) jsonObj.get("expression"));
		
		for(Object argument : (JSONArray) jsonObj.get("arguments")) {
			this.arguments.add(Expression.getExpression((JSONObject) argument));
		}
		
		if(jsonObj.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
		
		for(Object name : (JSONArray)jsonObj.get("names")) {
			this.names.add((String)name);
		}
	}

	//getter
	public Vector<Expression> getArguments() { return arguments; }
	public Vector<HashMap<String, String>> getArgumentTypes() {	return argumentTypes; }
	public Expression getExpression() {	return expression; }
	public String getKind() { return kind; }
	public Vector<String> getNames() { return names; }
	public boolean isTryCall() { return tryCall; }
}
