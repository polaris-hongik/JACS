package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class ElementaryTypeName extends TypeName {
	private String name;
	private String stateMutability;

	public ElementaryTypeName(JSONObject node) {
		super(node);
		this.name = (String) node.get("name");
		this.stateMutability = (String) node.get("stateMutability");
	}

	//getter
	public String getName() { return name; }
	public String getStateMutability() { return stateMutability; }
}
