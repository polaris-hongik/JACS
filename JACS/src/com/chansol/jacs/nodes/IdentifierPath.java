package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class IdentifierPath extends Node{
    private String name;
    private long referencedDeclaration;
    
    public IdentifierPath(JSONObject jsonObj) {
    	super(jsonObj);
    	this.name = jsonObj.get("name").toString();
    	this.referencedDeclaration = (long) jsonObj.get("referencedDeclaration");
    }

    //getter
	public String getName() { return name; }
	public long getReferencedDeclaration() { return referencedDeclaration; }
}
