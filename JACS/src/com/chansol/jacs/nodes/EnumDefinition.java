package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EnumDefinition extends Definition{
	private Vector<EnumValue> members = new Vector<EnumValue>();
	private String canonicalName;
	
	public EnumDefinition(JSONObject jsonObj) {
		super(jsonObj);
		this.canonicalName = (String) jsonObj.get("canonicalName");
		 
		for(Object member : (JSONArray)jsonObj.get("members")) {
			this.members.add(new EnumValue((JSONObject) member));
		}
	}

	//getter
	public Vector<EnumValue> getMembers() { return members; }
	public String getCanonicalName() { return canonicalName; }
}
