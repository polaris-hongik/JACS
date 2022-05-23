package com.chansol.jacs.nodes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class PragmaDirective extends Node{
	private String pragma;
	
	public PragmaDirective(JSONObject jsonObj){
		super(jsonObj);
		JSONArray literals = (JSONArray) jsonObj.get("literals");
		for(Object literal:literals) {
			this.pragma += (String) literal;
		}
	}
	
	//getter
	public String getPragma() {	return pragma; }
}
