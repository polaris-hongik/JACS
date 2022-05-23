package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class InheritanceSpecifier extends Node{
    private Node baseName;
    private Vector<Expression> arguments = new Vector<Expression>();
    
	public InheritanceSpecifier(JSONObject jsonObj) {
		super(jsonObj);
		if(((JSONObject) jsonObj.get("baseName")).get("nodeType").equals("UserDefinedTypeName")) {
			this.baseName = new UserDefinedTypeName((JSONObject) jsonObj.get("baseName"));
		}else if(((JSONObject) jsonObj.get("baseName")).get("nodeType").equals("IdentifierPath")) {
			this.baseName = new IdentifierPath((JSONObject) jsonObj.get("baseName"));
		}
		if((JSONArray)jsonObj.get("arguments") != null) {
			for(Object argument : (JSONArray)jsonObj.get("arguments")) {
				arguments.add(Expression.getExpression((JSONObject) argument));
			}
		}
	}

	//getter
	public Node getBaseName() { return baseName; }
	public Vector<Expression> getArguments() { return arguments; }
}
