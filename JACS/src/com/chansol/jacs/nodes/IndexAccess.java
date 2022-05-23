package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class IndexAccess extends Expression {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
    private Expression indexExpression;
	private Expression baseExpression;
	
	public IndexAccess(JSONObject jsonObj) {
		super(jsonObj);
		if(jsonObj.get("indexExpression") != null) {
			this.indexExpression = Expression.getExpression((JSONObject) jsonObj.get("indexExpression"));
		}
		this.baseExpression = Expression.getExpression((JSONObject) jsonObj.get("baseExpression"));
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
	public Expression getIndexExpression() { return indexExpression; }
	public Expression getBaseExpression() {	return baseExpression; }
	public Vector<HashMap<String,String>> getArgumentTypes() { return argumentTypes; }
}
