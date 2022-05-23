package com.selab.jacs;

import java.util.HashSet;

import org.json.simple.JSONObject;

public class Statement {
    protected long id;
    protected String nodeType;
    protected String src;
	protected HashSet<String> keys;

    public Statement(JSONObject node){
    	this.id = (long)node.get("id");
    	this.nodeType = (String)node.get("nodeType");
    	this.src = (String)node.get("src");
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
	public HashSet<String> keySet(){
		return keys;
	}
}
