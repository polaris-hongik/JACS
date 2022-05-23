package com.selab.jacs;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class NewExpression extends Value {
	private Vector<TypeDescriptions> argumentTypes = new Vector<TypeDescriptions>();
	private TypeName typeName;
	
	public NewExpression(JSONObject node) {
		super(node);
		if(node.get("argumentTypes") != null) {
			JSONArray argumentTypes = (JSONArray) node.get("argumentTypes");
			for(Object argumentType : argumentTypes) {
				this.argumentTypes.add(new TypeDescriptions((JSONObject) argumentType));
			}
		}
		JSONObject typeName = (JSONObject) node.get("typeName");
		if(typeName.get("nodeType").equals("UserDefinedTypeName")) {
			this.typeName = new UserDefinedTypeName(typeName);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(typeName.get("nodeType"));
			System.out.println("At id:"+typeName.get("id"));
		}
	}

	public Vector<TypeDescriptions> getArgumentTypes() {
		return argumentTypes;
	}

	public TypeName getTypename() {
		return typeName;
	}
}
