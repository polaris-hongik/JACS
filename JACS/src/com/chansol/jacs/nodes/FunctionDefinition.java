package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FunctionDefinition extends Definition{
	private Boolean virtual;
	private String visibility;
	private String kind;
	private String stateMutability;
	private ParameterList returnParameters;
	private Block body;
	private ParameterList parameters;
	private Vector<ModifierInvocation> modifiers = new Vector<ModifierInvocation>();
	private long scope;
	private Boolean implemented;
	private String functionSelector;
	private OverrideSpecifier overrides;
	private Vector<Long> baseFunctions = new Vector<Long>();//nullable
	
	public FunctionDefinition(JSONObject jsonObj) {
		super(jsonObj);
		
		this.virtual = (Boolean) jsonObj.get("virtual");
		this.visibility = (String) jsonObj.get("visibility");
		this.kind = (String) jsonObj.get("kind");
		this.stateMutability = (String) jsonObj.get("stateMutability");
		this.scope = (long) jsonObj.get("scope");
		this.implemented = (Boolean) jsonObj.get("implemented");
		this.functionSelector = (String) jsonObj.get("functionSelector");
		if(jsonObj.get("overrides") != null) {
			this.overrides = new OverrideSpecifier((JSONObject) jsonObj.get("overrides"));
		}
		if(jsonObj.get("body") != null) {
			this.body = new Block((JSONObject) jsonObj.get("body"));
		}
		this.parameters = new ParameterList((JSONObject) jsonObj.get("parameters"));
		this.returnParameters = new ParameterList((JSONObject) jsonObj.get("returnParameters"));
		
		for (Object modifier : (JSONArray) jsonObj.get("modifiers")) {
			modifiers.add(new ModifierInvocation((JSONObject)modifier));
		}
		if(jsonObj.get("baseFunctions") != null) {
			for (Object baseFunction : (JSONArray) jsonObj.get("baseFunctions")) {
				baseFunctions.add((long)baseFunction);
			}
		}
	}
	
	//getter
	public Boolean getVirtual() { return virtual; }
	public String getVisibility() { return visibility; }
	public String getKind() { return kind; }
	public String getStateMutability() { return stateMutability; }
	public ParameterList getReturnParameters() { return returnParameters; }
	public Block getBody() { return body; }
	public ParameterList getParameters() { return parameters; }
	public Vector<ModifierInvocation> getModifiers() { return modifiers; }
	public long getScope() { return scope; }
	public Boolean getImplemented() { return implemented; }
	public String getFunctionSelector() { return functionSelector; }
	public OverrideSpecifier getOverrides() { return overrides; }
	public Vector<Long> getBaseFunctions() { return baseFunctions; }
}
