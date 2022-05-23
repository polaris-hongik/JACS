package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class UnaryOperation extends Operation{
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private Expression subExpression;
	private Boolean prefix;

	public UnaryOperation(JSONObject jsonObj) {
		super(jsonObj);
		this.prefix = (Boolean) jsonObj.get("prefix");
		this.subExpression = Expression.getExpression((JSONObject) jsonObj.get("subExpression"));
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
	public Boolean getPrefix() { return prefix;	}
	public Expression getSubExpression() { return subExpression; }
	public Vector<HashMap<String,String>> getArgumentTypes() { return argumentTypes; }

	
}
