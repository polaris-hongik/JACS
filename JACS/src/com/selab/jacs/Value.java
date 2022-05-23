package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class Value {
	protected Long id;
	protected Boolean isConstant;
	protected Boolean isLValue;
	protected Boolean isPure;
	protected Boolean lValueRequested;
	protected String nodeType;
	protected String src;
	protected TypeDescriptions typeDescriptions;
	protected HashSet<String> keys;

	
	public Value(JSONObject node) {
		this.id = (Long)node.get("id");
		this.isConstant = (Boolean)node.get("isConstant");
		this.isLValue = (Boolean)node.get("isConstant");
		this.isPure = (Boolean)node.get("isPure");
		this.lValueRequested = (Boolean)node.get("lValueRequested");
		this.nodeType = (String)node.get("nodeType");
		this.src = (String)node.get("src");
		this.typeDescriptions = new TypeDescriptions((JSONObject) node.get("typeDescriptions"));
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}
	
	public Long getId() {
		return id;
	}

	public Boolean isConstant() {
		return isConstant;
	}

	public Boolean isLValue() {
		return isLValue;
	}

	public Boolean isPure() {
		return isPure;
	}

	public Boolean islValueRequested() {
		return lValueRequested;
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
