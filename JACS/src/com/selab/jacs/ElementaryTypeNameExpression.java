package com.selab.jacs;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ElementaryTypeNameExpression extends Value {
	private Vector<TypeDescriptions> argumentTypes = new Vector<TypeDescriptions>();
	private ElementaryTypeName typeName;
	
	public ElementaryTypeNameExpression(JSONObject node) {
		super(node);
		JSONArray argumentTypes = (JSONArray) node.get("argumentTypes");
		for(Object argumentType: argumentTypes) {
			this.argumentTypes.add(new TypeDescriptions((JSONObject) argumentType));
		}
		this.typeName = new ElementaryTypeName((JSONObject) node.get("typeName"));
	}

}
