package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class Mapping {
	private Long id;
	private String nodeType;
	private String src;
	private TypeName keyType;
	private TypeName valueType;
	private TypeDescriptions typeDescriptions;
	private HashSet<String> keys;
	
	public Mapping(JSONObject node) {
		this.id = (Long)node.get("id");
		this.nodeType = (String)node.get("nodeType");
		this.src = (String)node.get("src");
		JSONObject keyType = (JSONObject) node.get("keyType");
		if(keyType.get("nodeType").equals("ElementaryTypeName")) {
			this.keyType = new ElementaryTypeName(keyType);
		}else if(keyType.get("nodeType").equals("UserDefinedTypeName")) {
			this.keyType = new UserDefinedTypeName(keyType);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(keyType.get("nodeType"));
			System.out.println("At id:"+keyType.get("id"));
		}
		JSONObject valueType = (JSONObject) node.get("valueType");
		if(valueType.get("nodeType").equals("ElementaryTypeName")) {
			this.valueType = new ElementaryTypeName(valueType);
		}else if(valueType.get("nodeType").equals("UserDefinedTypeName")) {
			this.valueType = new UserDefinedTypeName(valueType);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(valueType.get("nodeType"));
			System.out.println("At id:"+valueType.get("id"));
		}
		
		this.typeDescriptions = new TypeDescriptions((JSONObject) node.get("typeDescriptions"));
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}
	
	public Long getId() {
		return id;
	}
	public String getNodeType() {
		return nodeType;
	}
	public String getSrc() {
		return src;
	}
	public TypeName getKeyType() {
		return keyType;
	}
	public TypeName getValueType() {
		return valueType;
	}
	public TypeDescriptions getTypeDescriptions() {
		return typeDescriptions;
	}
	public HashSet<String> keySet(){
		return keys;
	}
}
