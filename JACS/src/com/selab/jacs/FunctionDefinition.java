package com.selab.jacs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FunctionDefinition {
	private Boolean virtual;
	private String visibility;
	private String src;
	private String kind;
	private String stateMutability;
	private String name;
	private long id;
	private String nameLocation;
	private ParameterList returnParameters;
	private Block body; // nullable
	private String nodeType;
	private ParameterList parameters;
	private Vector<ModifierInvocation> modifiers = new Vector<ModifierInvocation>();
	private long scope;
	private Boolean implemented;
	private String functionSelector;
	private OverrideSpecifier overrides;//nullable
	private Vector<Long> baseFunctions = new Vector<Long>();//nullable
	private Object documentation;//null값 이외의 값이 나오면 추가
	private HashSet<String> keys = new HashSet<String>(Arrays.asList("virtual","visibility","src","kind","stateMutability","name","id","nameLocation","returnParameters",
																	"nodeType","body","parameters","modifiers","scope","implemented","functionSelector","overrides","baseFunctions","documentation"));
	
	public FunctionDefinition(JSONObject node) {
		this.virtual = (Boolean) node.get("virtual");
		this.visibility = (String) node.get("visibility");
		this.src = (String) node.get("src");
		this.kind = (String) node.get("kind");
		this.stateMutability = (String) node.get("stateMutability");
		this.name = (String) node.get("name");
		this.id = (long) node.get("id");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.scope = (long) node.get("scope");
		this.implemented = (Boolean) node.get("implemented");
		this.functionSelector = (String) node.get("functionSelector");
		if(node.get("overrides") != null) {
			this.overrides = new OverrideSpecifier((JSONObject) node.get("overrides"));
		}
		if(node.get("body") != null) {
			this.body = new Block((JSONObject) node.get("body"));
		}
		this.parameters = new ParameterList((JSONObject) node.get("parameters"));
		this.returnParameters = new ParameterList((JSONObject) node.get("returnParameters"));
		this.documentation = node.get("documentation");
		if(documentation != null) {
			System.out.println("!key on FunctionDefinition -> documentation is not null!");
		}
		
		JSONArray modifierArr = (JSONArray) node.get("modifiers");
		for (Object modifier : modifierArr) {
			modifiers.add(new ModifierInvocation((JSONObject)modifier));
		}
		if(node.get("baseFunctions") != null) {
			JSONArray baseFunctionArr = (JSONArray) node.get("baseFunctions");
			for (Object baseFunction : baseFunctionArr) {
				baseFunctions.add((long)baseFunction);
			}
		}
		HashSet<String> keys = new HashSet<String>(node.keySet());
		keys.removeAll(this.keys);
		if(!keys.isEmpty()) {
			System.out.println("=== Find new Key(s) ===");
			for(String newKey : keys) {
				System.out.println(newKey);
			}
			System.out.println("At "+this.getClass()+" Class:id:"+this.id);
		}
		System.out.println(id);
	}
	public Boolean getVirtual() {
		return virtual;
	}
	public String getVisibility() {
		return visibility;
	}
	public String getSrc() {
		return src;
	}
	public String getKind() {
		return kind;
	}
	public String getStateMutability() {
		return stateMutability;
	}
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public String getNameLocation() {
		return nameLocation;
	}
	public ParameterList getReturnParameters() {
		return returnParameters;
	}
	public Block getBody() {
		return body;
	}
	public String getNodeType() {
		return nodeType;
	}
	public ParameterList getParameters() {
		return parameters;
	}
	public Vector<ModifierInvocation> getModifiers() {
		return modifiers;
	}
	public long getScope() {
		return scope;
	}
	public Boolean getImplemented() {
		return implemented;
	}
	public String getFunctionSelector() {
		return functionSelector;
	}
	public OverrideSpecifier getOverrides() {
		return overrides;
	}
	public Vector<Long> getBaseFunctions() {
		return baseFunctions;
	}
	public HashSet<String> keySet(){
		return keys;
	}
}
