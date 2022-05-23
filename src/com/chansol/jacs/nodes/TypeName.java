package com.chansol.jacs.nodes;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class TypeName extends Node{
	protected HashMap<String,String> typeDescriptions = new HashMap<String,String>();
	protected Boolean isConstant;
	protected Boolean isLValue;
	protected Boolean isPure;
	protected Boolean lValueRequested;
	
	public TypeName(JSONObject jsonObj) {
		super(jsonObj);
		JSONObject typeDescriptions = (JSONObject) jsonObj.get("typeDescriptions");
		if(typeDescriptions.get("typeIdentifier") == null) {
			this.typeDescriptions.put("typeIdentifier", "null");
		}else {
			this.typeDescriptions.put("typeIdentifier", typeDescriptions.get("typeIdentifier").toString());
		}
		if(typeDescriptions.get("typeString") == null) {
			this.typeDescriptions.put("typeString", "null");
		}else {
			this.typeDescriptions.put("typeString", typeDescriptions.get("typeString").toString());
		}
		this.isConstant = (Boolean)jsonObj.get("isConstant");
		this.isLValue = (Boolean)jsonObj.get("isConstant");
		this.isPure = (Boolean)jsonObj.get("isPure");
		this.lValueRequested = (Boolean)jsonObj.get("lValueRequested");
	}

	protected static TypeName getTypeName(JSONObject jsonObj) {
		TypeName typedTypeName = null;
		
		if(jsonObj.get("nodeType").equals("ElementaryTypeName")) {
			typedTypeName = new ElementaryTypeName(jsonObj);
		}else if(jsonObj.get("nodeType").equals("Mapping")) {
			typedTypeName = new Mapping(jsonObj);
		}else if(jsonObj.get("nodeType").equals("ArrayTypeName")) {
			typedTypeName = new ArrayTypeName(jsonObj);
		}else if(jsonObj.get("nodeType").equals("UserDefinedTypeName")) {
			typedTypeName = new UserDefinedTypeName(jsonObj);
		}else {
			System.out.println("=== Find new typeName ===");
			System.out.println(jsonObj.get("nodeType"));
			System.out.println("At id: "+jsonObj.get("id"));
		}
		return typedTypeName;
	}

	//getter
	public Boolean isConstant() { return isConstant; }
	public Boolean isLValue() {	return isLValue; }
	public Boolean isPure() { return isPure; }
	public Boolean islValueRequested() { return lValueRequested; }
}
