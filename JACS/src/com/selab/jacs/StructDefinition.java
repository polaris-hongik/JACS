package com.selab.jacs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StructDefinition {
	private String visibility;
	private String src;
	private Vector<VariableDeclaration> members = new Vector<VariableDeclaration>();
	private Long scope;
	private String name;
	private Long id;
	private String nameLocation;
	private String nodeType;
	private String canonicalName;
	private HashSet<String> keys = new HashSet<String>(Arrays.asList("visibility","src","scope","name","id","nameLocation","nodeType","canonicalName","members"));
	
	public StructDefinition(JSONObject node) {
		this.visibility = (String) node.get("visibility");
		this.src = (String) node.get("src");
		this.scope = (Long)node.get("scope");
		this.name = (String) node.get("name");
		this.id = (Long) node.get("id");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.canonicalName = (String)node.get("canonicalName");
		JSONArray membersArr = (JSONArray)node.get("members");
		for(Object member : membersArr) {
			this.members.add(new VariableDeclaration((JSONObject) member));
		}
		
		HashSet<String> keys = new HashSet<String>(node.keySet());
		keys.removeAll(this.keys);
		if(!keys.isEmpty()) {
			System.out.println("=== Find new Key(s) ===");
			for(String newKey : keys) {
				System.out.println(newKey);
			}
			System.out.println("At "+this.getClass()+" Class:id:"+this.id);
		}
		System.out.println(id);
	}

	public String getVisibility() {
		return visibility;
	}

	public String getSrc() {
		return src;
	}

	public Vector<VariableDeclaration> getMembers() {
		return members;
	}

	public Long getScope() {
		return scope;
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
