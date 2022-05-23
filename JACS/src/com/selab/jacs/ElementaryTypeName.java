package com.selab.jacs;

import org.json.simple.JSONObject;

public class ElementaryTypeName extends TypeName {
	private String name;

	public ElementaryTypeName(JSONObject node) {
		super(node);
		this.name = (String) node.get("name");
	}

	public String getName() {
		return name;
	}
}
