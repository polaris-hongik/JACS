package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ElementaryTypeNameExpression extends Expression {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private ElementaryTypeName typeName;
	
	public ElementaryTypeNameExpression(JSONObject jsonObj) {
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
		this.typeName = new ElementaryTypeName((JSONObject) jsonObj.get("typeName"));
	}

	//getter
	public Vector<HashMap<String, String>> getArgumentTypes() {	return argumentTypes; }
	public ElementaryTypeName getTypeName() { return typeName; }
}
