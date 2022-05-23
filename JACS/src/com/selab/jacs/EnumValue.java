package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class EnumValue {
	private long id;
	private String name;
	private String nameLocation;
	private String nodeType;
	private String src;
	private HashSet<String> keys;
	
	public EnumValue(JSONObject node) {
		this.id = (long) node.get("id");
		this.name = (String) node.get("name");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.src = (String) node.get("src");
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public String getNodeType() {
		return nodeType;
	}

	public String getSrc() {
		return src;
	}
	
	public HashSet<String> keySet(){
		return keys;
	}
}
