package com.selab.jacs;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PragmaDirective {
	private String src;
	private String pragma;
	private long id;
	private String nodeType;
	
	public PragmaDirective(JSONObject node){
		this.src = (String) node.get("src");
		JSONArray literals = (JSONArray) node.get("literals");
		for(Object literal:literals) {
			this.pragma += (String) literal;
		}
		this.id = (long) node.get("id");
		this.nodeType = (String) node.get("nodeType");
		System.out.println(id);
	}
	
	public String getSrc() {
		return src;
	}
	public String getPragma() {
		return pragma;
	}
	public long getId() {
		return id;
	}
	public String getNodeType() {
		return nodeType;
	}
}
