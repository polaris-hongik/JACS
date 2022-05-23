package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class EventDefinition extends Definition{
	private Boolean anonymous;
	private ParameterList parameters;
	
	public EventDefinition(JSONObject jsonObj) {
		super(jsonObj);
		this.anonymous = (Boolean) jsonObj.get("anonymous");
		this.parameters = new ParameterList((JSONObject)jsonObj.get("parameters"));
	}
	
	//getter
	public Boolean isAnonymous() { return anonymous; }
	public ParameterList getParameters() { return parameters; }
}
