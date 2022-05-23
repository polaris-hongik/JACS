package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Identifier extends Value{
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private String name;
    private long referencedDeclaration;
    private Vector<Long> overloadedDeclarations = new Vector<Long>();

	public Identifier(JSONObject node) {
		super(node);
		this.name = (String) node.get("name");
		
		
		if(node.get("referencedDeclaration") != null) {
			this.referencedDeclaration = (long) node.get("referencedDeclaration");
		}
		JSONArray argumentTypes = (JSONArray)node.get("argumentTypes");
		if(argumentTypes != null){
			for(Object argumentTypeO: (JSONArray) node.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
		
		JSONArray overloadedDeclarationArr = (JSONArray)node.get("overloadedDeclarations");
		if(overloadedDeclarationArr != null){
			for(Object overloadedDeclaration : overloadedDeclarationArr) {
				this.overloadedDeclarations.add((Long)overloadedDeclaration);
			}
		}
	}
	
	//getter
	public Vector<HashMap<String,String>> getArgumentTypes() { return argumentTypes; }
	public String getName() { return name; }
	public long getReferencedDeclaration() { return referencedDeclaration; }
	public Vector<Long> getOverloadedDeclarations() { return overloadedDeclarations; }
}
