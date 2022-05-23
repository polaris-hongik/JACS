package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class StructuredDocumentation extends Node {
	private String text;
	
	public StructuredDocumentation(JSONObject jsonObj) {
		super(jsonObj);
		
		this.text = (String) jsonObj.get("text");
	}
	
	//gettter
	public String getText() { return text; };
}
