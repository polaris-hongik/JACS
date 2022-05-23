package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class TypeName {
	protected long id;
	protected String src;
	protected TypeDescriptions typeDescriptions;
	protected HashSet<String> keys;
	protected String nodeType;
	
	public TypeName(JSONObject node) {
		this.id = (long) node.get("id");
		this.nodeType = (String) node.get("nodeType");
		this.src = (String) node.get("src");
		this.typeDescriptions = new TypeDescriptions((JSONObject)node.get("typeDescriptions"));
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}
	public long getId() {
		return id;
	}

	public String getNodeType() {
		return nodeType;
	}

	public String getSrc() {
		return src;
	}

	public TypeDescriptions getTypeDescriptions() {
		return typeDescriptions;
	}
	
	public HashSet<String> keySet(){
		return keys;
	}
}
