package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class InheritanceSpecifier {
    private IdentifierPath baseName;
    private long id;
    private String nodeType;
    private String src;
	private HashSet<String> keys;
    
	public InheritanceSpecifier(JSONObject node) {
		this.baseName = new IdentifierPath((JSONObject) node.get("baseName"));
		this.id = (long) node.get("id");
		this.nodeType = (String) node.get("nodeType");
		this.src = (String) node.get("src");
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}

	public IdentifierPath getBaseName() {
		return baseName;
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
	public HashSet<String> keySet(){
		return keys;
	}
}
