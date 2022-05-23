package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class OverrideSpecifier {
	private long id;
	private String nodeType;
	private String src;
	private HashSet<String> keys;
	private Vector<IdentifierPath> overrides = new Vector<IdentifierPath>();
	
	public OverrideSpecifier(JSONObject node) {
		this.id = (long)node.get("id");
    	this.nodeType = (String)node.get("nodeType");
    	this.src = (String)node.get("src");
    	JSONArray overrideArr = (JSONArray)node.get("overrides");
		for(Object override : overrideArr) {
			overrides.add(new IdentifierPath((JSONObject) override));
		}
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
	public Vector<IdentifierPath> getOverrides() {
		return overrides;
	}

	public HashSet<String> keySet(){
		return keys;
	}
}
