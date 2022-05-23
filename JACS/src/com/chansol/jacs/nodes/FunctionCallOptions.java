package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FunctionCallOptions extends Expression {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
    private Vector<String> names = new Vector<String>(); // ÀÌ°Ô ¹¹Áö
    private Vector<Expression> options = new Vector<Expression>();
    private Expression expression;

	public FunctionCallOptions(JSONObject jsonObj) {
		super(jsonObj);
		
		this.expression = Expression.getExpression((JSONObject) jsonObj.get("expression"));
		for(Object name : (JSONArray)jsonObj.get("names")) {
			this.names.add((String)name);
		}
		for(Object option : (JSONArray)jsonObj.get("options")) {
			this.options.add(Expression.getExpression((JSONObject) option));
		}
		JSONArray argumentTypes = (JSONArray)jsonObj.get("argumentTypes");
		if(argumentTypes != null){
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
	public Vector<String> getNames() { return names; }
	public Vector<Expression> getOptions() { return options; }
	public Expression getExpression() { return expression; }
	public Vector<HashMap<String,String>> getArgumentTypes() { return argumentTypes; }
}
