package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class VariableDeclarationStatement extends Statement{
	private Vector<Long> assignments = new Vector<Long>();
	private Vector<VariableDeclaration> declarations = new Vector<VariableDeclaration>();
	private Expression initialValue;

	public VariableDeclarationStatement(JSONObject jsonObj) {
		super(jsonObj);
		for(Object assignment : (JSONArray) jsonObj.get("assignments")) {
			this.assignments.add((Long)assignment);
		}
		for(Object declaration : (JSONArray) jsonObj.get("declarations")) {
			if(declaration != null) {
				this.declarations.add(new VariableDeclaration((JSONObject) declaration));
			}
		}
		if(jsonObj.get("initialValue") != null) {
			this.initialValue = Expression.getExpression((JSONObject) jsonObj.get("initialValue"));
		}
	}
	
	//getter
	public Vector<Long> getAssignments() { return assignments; }
	public Vector<VariableDeclaration> getDeclarations() { return declarations; }
	public Expression getInitialValue() { return initialValue; }
}
