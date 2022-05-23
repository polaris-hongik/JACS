package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BinaryOperation extends Operation{
	private HashMap<String,String> commonType = new HashMap<String,String>();
	private Expression leftExpression;
	private Expression rightExpression; 
	private Boolean prefix;
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	
	public BinaryOperation(JSONObject jsonObj) {
		super(jsonObj);
		this.prefix = (Boolean) jsonObj.get("prefix");
		this.leftExpression = Expression.getExpression((JSONObject) jsonObj.get("leftExpression"));
		this.rightExpression = Expression.getExpression((JSONObject) jsonObj.get("rightExpression"));
		JSONObject typeDescriptions = (JSONObject) jsonObj.get("typeDescriptions");
		this.typeDescriptions.put("typeIdentifier", (String)typeDescriptions.get("typeIdentifier"));
		this.typeDescriptions.put("typeString", (String)typeDescriptions.get("typeString"));
		
		if(jsonObj.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescription = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescription.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescription.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescription);
			}
		}
	}
	
	//getter
	public Boolean getPrefix() { return prefix; }
	public HashMap<String,String> getCommonType() { return commonType; }
	public Expression getLeftExpression() { return leftExpression; }
	public Expression getRightExpression() {	return rightExpression;	}
}
