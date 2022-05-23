package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Assignment extends Operation {
	private Expression leftHandSide;
	private Expression rightHandSide; 
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();

	
	public Assignment(JSONObject jsonObj) {
		super(jsonObj);
		
		if(jsonObj.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
		this.leftHandSide = Expression.getExpression((JSONObject) jsonObj.get("leftHandSide"));
		this.rightHandSide = Expression.getExpression((JSONObject) jsonObj.get("rightHandSide"));
	}
	
	//getter
	public Expression getLeftHandSide() { return leftHandSide; }
	public Expression getRightHandSide() { return rightHandSide; }
	public Vector<HashMap<String, String>> getArgumentTypes() {	return argumentTypes; }
}
