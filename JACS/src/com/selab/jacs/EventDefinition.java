package com.selab.jacs;

import java.util.Arrays;
import java.util.HashSet;

import org.json.simple.JSONObject;

public class EventDefinition {
	private String src;
	private String name;
	private Boolean anonymous;
	private Long id;
	private String nameLocation;
	private String nodeType;
	private ParameterList parameters;
	private Object documentation; //null값 이외의 값이 나오면 추가
	private HashSet<String> keys = new HashSet<String>(Arrays.asList("src","name","anonymous","id","nameLocation","nodeType","parameters","documentation"));
	
	public EventDefinition(JSONObject node) {
		this.src = (String) node.get("src");
		this.name = (String) node.get("name");
		this.anonymous = (Boolean) node.get("anonymous");
		this.id = (Long) node.get("id");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.parameters = new ParameterList((JSONObject)node.get("parameters"));
		this.documentation = node.get("documentation");
		if(documentation != null) {
			System.out.println("!key on FunctionDefinition -> documentation is not null!");
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

	public String getSrc() {
		return src;
	}

	public String getName() {
		return name;
	}

	public Boolean isAnonymous() {
		return anonymous;
	}

	public Long getId() {
		return id;
	}

	public String getNameLocation() {
		return nameLocation;
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
