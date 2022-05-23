package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParameterList {
    private Long id;
    private String nodeType;
    private Vector<VariableDeclaration> parameters = new Vector<VariableDeclaration>();
    private String src;
    private HashSet<String> keys;
    
	public ParameterList(JSONObject node) {
		this.src = (String) node.get("src");
		this.id = (Long) node.get("id");
		this.nodeType = (String) node.get("nodeType");
		
		JSONArray parameterArr = (JSONArray)node.get("parameters");
		for(Object parameter : parameterArr) {
			this.parameters.add(new VariableDeclaration((JSONObject) parameter));
		}
		
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}
	public Long getId() {
		return id;
	}
	public String getNodeType() {
		return nodeType;
	}
	public Vector<VariableDeclaration> getParameters() {
		return parameters;
	}
	public String getSrc() {
		return src;
	}
	public HashSet<String> keySet(){
		return keys;
	}
}
