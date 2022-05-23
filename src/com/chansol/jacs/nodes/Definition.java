package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class Definition extends Node{
	protected String nameLocation;
	protected String name;
	private StructuredDocumentation documentation;
	
	public Definition(JSONObject jsonObj) {
		super(jsonObj);
		this.nameLocation = (String) jsonObj.get("nameLocation");
		this.name = (String) jsonObj.get("name");
		if(jsonObj.get("documentation") != null) {
			this.documentation = new StructuredDocumentation((JSONObject) jsonObj.get("documentation"));
		}
	}
	
	//getter
	public String getNameLocation() { return this.nameLocation; }
	public String getName() { return this.name; }
	public StructuredDocumentation getDocumentation() { return documentation; }
}
