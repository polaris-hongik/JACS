package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EnumDefinition {
	private String src;
	private Vector<EnumValue> members = new Vector<EnumValue>();
	private String name;
	private Long id;
	private String nameLocation;
	private String nodeType;
	private String canonicalName;
	private HashSet<String> keys;
	
	public EnumDefinition(JSONObject node) {
		this.src = (String) node.get("src");
		this.name = (String) node.get("name");
		this.id = (Long) node.get("id");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.canonicalName = (String) node.get("canonicalName");
		 
		JSONArray membersArr = (JSONArray)node.get("members");
		for(Object member : membersArr) {
			this.members.add(new EnumValue((JSONObject) member));
		}
		
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}

	public String getSrc() {
		return src;
	}

	public Vector<EnumValue> getMembers() {
		return members;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public String getNodeType() {
		return nodeType;
	}

	public String getCanonicalName() {
		return canonicalName;
	}
	
	public HashSet<String> keySet(){
		return keys;
	}
}
