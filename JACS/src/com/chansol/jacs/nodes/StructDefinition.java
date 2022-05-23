package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StructDefinition extends Definition{
	private String visibility;
	private Vector<VariableDeclaration> members = new Vector<VariableDeclaration>();
	private Long scope;
	private String canonicalName;
	
	public StructDefinition(JSONObject jsonObj) {
		super(jsonObj);
		this.visibility = (String) jsonObj.get("visibility");
		this.scope = (Long)jsonObj.get("scope");
		this.canonicalName = (String)jsonObj.get("canonicalName");
		for(Object member : (JSONArray)jsonObj.get("members")) {
			this.members.add(new VariableDeclaration((JSONObject) member));
		}
	}

	//getter
	public String getVisibility() {	return visibility; }
	public Vector<VariableDeclaration> getMembers() { return members; }
	public long getScope() { return scope; }
	public String getCanonicalName() { return canonicalName; }
}
