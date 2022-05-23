package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ParameterList extends Node{
    private Vector<VariableDeclaration> parameters = new Vector<VariableDeclaration>();
    
	public ParameterList(JSONObject jsonObj) {
		super(jsonObj);
		JSONArray parameterArr = (JSONArray)jsonObj.get("parameters");
		for(Object parameter : parameterArr) {
			this.parameters.add(new VariableDeclaration((JSONObject) parameter));
		}
	}
	
	//getter
	public Vector<VariableDeclaration> getParameters() { return parameters;	}
}
