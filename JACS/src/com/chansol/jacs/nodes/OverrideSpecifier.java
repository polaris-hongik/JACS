package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OverrideSpecifier extends Node {
	private Vector<IdentifierPath> overrides = new Vector<IdentifierPath>();
	
	public OverrideSpecifier(JSONObject jsonObj) {
		super(jsonObj);
		for(Object override : (JSONArray)jsonObj.get("overrides")) {
			overrides.add(new IdentifierPath((JSONObject) override));
		}
	}
	
	//getter
	public Vector<IdentifierPath> getOverrides() { return overrides; }
}
