package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class VariableDeclarationStatement extends Statement{
	private Vector<Long> assignments = new Vector<Long>();
	private Vector<VariableDeclaration> declarations = new Vector<VariableDeclaration>();
	private Value initialValue;

	public VariableDeclarationStatement(JSONObject node) {
		super(node);
		JSONArray assignments = (JSONArray) node.get("assignments");
		for(Object assignment : assignments) {
			this.assignments.add((Long)assignment);
		}
		JSONArray declarations = (JSONArray) node.get("declarations");
		for(Object declaration : declarations) {
			if(declaration != null) {
				this.declarations.add(new VariableDeclaration((JSONObject) declaration));
			}
		}
		JSONObject initialValue = (JSONObject) node.get("initialValue");
		if(initialValue!=null) {
			if(initialValue.get("nodeType").equals("Literal")) {
				this.initialValue = new Literal(initialValue);
			}else if(initialValue.get("nodeType").equals("UnaryOperation")) {
				this.initialValue = new UnaryOperation(initialValue);
			}else if(initialValue.get("nodeType").equals("BinaryOperation")) {
				this.initialValue = new BinaryOperation(initialValue);
			}else if(initialValue.get("nodeType").equals("Identifier")) {
				this.initialValue = new Identifier(initialValue);
			}else if(initialValue.get("nodeType").equals("MemberAccess")) {
				this.initialValue = new MemberAccess(initialValue);
			}else if(initialValue.get("nodeType").equals("IndexAccess")) {
				this.initialValue = new IndexAccess(initialValue);
			}else if(initialValue.get("nodeType").equals("FunctionCall")) {
				this.initialValue = new FunctionCall(initialValue);
			}else {
				System.out.println("=== Find new Value ===");
				System.out.println(initialValue.get("nodeType"));
				System.out.println("At id:"+initialValue.get("id"));
				System.out.println("At Parent NodeType:"+this.getNodeType());
			}
		}
	}
	public Vector<Long> getAssignments() {
		return assignments;
	}
	public Vector<VariableDeclaration> getDeclarations() {
		return declarations;
	}
	public Value getInitialValue() {
		return initialValue;
	}
}
