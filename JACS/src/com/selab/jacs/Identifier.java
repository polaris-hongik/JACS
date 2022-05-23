package com.selab.jacs;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Identifier extends Value{
	private Vector<TypeDescriptions> argumentTypes = new Vector<TypeDescriptions>();
	private String name;
    private long referencedDeclaration;
    private Vector<Long> overloadedDeclarations = new Vector<Long>();

	public Identifier(JSONObject node) {
		super(node);
		this.name = (String) node.get("name");
		
		JSONArray argumentTypes = (JSONArray)node.get("argumentTypes");
		if(node.get("referencedDeclaration") != null) {
			this.referencedDeclaration = (long) node.get("referencedDeclaration");
		}
		if(argumentTypes != null){
			for(Object argumentType : argumentTypes) {
				this.argumentTypes.add(new TypeDescriptions((JSONObject) argumentType));
			}
		}
		
		JSONArray overloadedDeclarationArr = (JSONArray)node.get("overloadedDeclarations");
		if(overloadedDeclarationArr != null){
			for(Object overloadedDeclaration : overloadedDeclarationArr) {
				this.overloadedDeclarations.add((Long)overloadedDeclaration);
			}
		}
	}

	public Vector<TypeDescriptions> getArgumentTypes() {
		return argumentTypes;
	}

	public String getName() {
		return name;
	}

	public long getReferencedDeclaration() {
		return referencedDeclaration;
	}

	public Vector<Long> getOverloadedDeclarations() {
		return overloadedDeclarations;
	}

}
