package com.selab.jacs;

import java.util.Arrays;
import java.util.HashSet;

import org.json.simple.JSONObject;

public class ModifierDefinition {
	private Boolean virtual;
	private String visibility;
	private String src;
	private String name;
	private long id;
	private String nameLocation;
	private Block body;
	private String nodeType;
	private ParameterList parameters;
	private HashSet<String> keys = new HashSet<String>(Arrays.asList("virtual","visibility","src","name","id","nameLocation","nodeType","body","parameters"));

	public ModifierDefinition(JSONObject node) {
		this.virtual = (Boolean) node.get("virtual");
		this.visibility = (String) node.get("visibility");
		this.src = (String) node.get("src");
		this.name = (String) node.get("name");
		this.id = (long) node.get("id");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.body = new Block((JSONObject) node.get("body"));
		this.parameters = new ParameterList((JSONObject) node.get("parameters"));
		
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
	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public String getNameLocation() {
		return nameLocation;
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
	public HashSet<String> keySet(){
		return keys;
	}
}
