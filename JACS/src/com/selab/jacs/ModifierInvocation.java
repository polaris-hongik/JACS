package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ModifierInvocation {
    private Vector<Value> arguments = new Vector<Value>();
    private long id;
    private String kind;
    private IdentifierPath modifierName;
    private String nodeType;
    private String src;
	private HashSet<String> keys;

	public ModifierInvocation(JSONObject node) {
		this.id = (long)node.get("id");
		this.kind = (String)node.get("kind");
		this.modifierName = new IdentifierPath((JSONObject) node.get("modifierName"));
		this.nodeType = (String) node.get("nodeType");
		this.src = (String) node.get("src");
		this.keys = new HashSet<String>(node.keySet());
		JSONArray argumentArr = (JSONArray)node.get("arguments");
		if(argumentArr != null) {
			for(Object argumentO : argumentArr) {
			    JSONObject argument = (JSONObject)argumentO;
			    if(argument.get("nodeType").equals("Literal")) {
			    	arguments.add(new Literal((JSONObject) argument));
			    } else {
					System.out.println("=== Find new Value ===");
					System.out.println(argument.get("nodeType"));
					System.out.println("At id:"+argument.get("id"));
				}
			}
		}
		System.out.println(id);
	}
	
	public Vector<Value> getArguments() {
		return arguments;
	}
	public long getId() {
		return id;
	}
	public String getKind() {
		return kind;
	}
	public IdentifierPath getModifierName() {
		return modifierName;
	}
	public String getNodeType() {
		return nodeType;
	}
	public String getSrc() {
		return src;
	}
	public HashSet<String> keySet(){
		return keys;
	}
}
