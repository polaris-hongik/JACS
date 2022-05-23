package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ModifierInvocation extends Node{
    private Vector<Expression> arguments = new Vector<Expression>();
    private String kind;
    private Node modifierName;

	public ModifierInvocation(JSONObject jsonObj) {
		super(jsonObj);
		this.kind = (String)jsonObj.get("kind");
		if(((JSONObject) jsonObj.get("modifierName")).get("nodeType").equals("Identifier")) {
			this.modifierName = new Identifier((JSONObject) jsonObj.get("modifierName"));
		}else if(((JSONObject) jsonObj.get("modifierName")).get("nodeType").equals("IdentifierPath")) {
			this.modifierName = new IdentifierPath((JSONObject) jsonObj.get("modifierName"));
		}

		if((JSONArray)jsonObj.get("arguments") != null) {
			for(Object argument : (JSONArray)jsonObj.get("arguments")) {
				arguments.add(Expression.getExpression((JSONObject) argument));
			}
		}
	}
	
	//getter
	public Vector<Expression> getArguments() { return arguments; }
	public String getKind() { return kind; }
	public Node getModifierName() { return modifierName; }
}
