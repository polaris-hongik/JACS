package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class IdentifierPath {
    private long id;
    private String name;
    private String nodeType;
    private long referencedDeclaration;
    private String src;
	private HashSet<String> keys;
    
    public IdentifierPath(JSONObject node) {
    	this.id = (long) node.get("id");
    	this.name = (String) node.get("name");
    	this.nodeType = (String) node.get("nodeType");
    	this.referencedDeclaration = (long) node.get("referencedDeclaration");
    	this.src = (String) node.get("src");
    	this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
    }

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNodeType() {
		return nodeType;
	}

	public long getReferencedDeclaration() {
		return referencedDeclaration;
	}

	public String getSrc() {
		return src;
	}
	public HashSet<String> keySet(){
		return keys;
	}
}
