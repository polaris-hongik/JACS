package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NewExpression extends Expression {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private TypeName typeName;
	
	public NewExpression(JSONObject node) {
		super(node);
		if(node.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) node.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
		this.typeName = TypeName.getTypeName((JSONObject) node.get("typeName"));
	}

	public Vector<HashMap<String,String>> getArgumentTypes() {
		return argumentTypes;
	}

	public TypeName getTypename() {
		return typeName;
	}
}
