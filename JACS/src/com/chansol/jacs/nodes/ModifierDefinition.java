package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class ModifierDefinition extends Definition{
	private Boolean virtual;
	private String visibility;
	private Block body;
	private ParameterList parameters;
	private OverrideSpecifier overrides;

	public ModifierDefinition(JSONObject jsonObj) {
		super(jsonObj);
		this.virtual = (Boolean) jsonObj.get("virtual");
		this.visibility = (String) jsonObj.get("visibility");
		this.body = new Block((JSONObject) jsonObj.get("body"));
		this.parameters = new ParameterList((JSONObject) jsonObj.get("parameters"));
		if(jsonObj.get("overrides") != null) {
			this.overrides = new OverrideSpecifier((JSONObject) jsonObj.get("overrides"));
		}
	}
	
	//getter
	public Boolean getVirtual() { return virtual; }
	public String getVisibility() {	return visibility; }
	public Block getBody() { return body; }
	public ParameterList getParameters() { return parameters; }
	public OverrideSpecifier getOverrides() { return overrides; }
}
