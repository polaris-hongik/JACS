package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Block {
	private long id;
	private String nodeType;
	private String src;
	private Vector<Statement> statements = new Vector<Statement>();
	protected HashSet<String> keys;
	
	public Block(JSONObject node) {
		this.id = (long) node.get("id");
		this.nodeType = (String) node.get("nodeType");
		this.src = (String) node.get("src");
		
		JSONArray statementArr = (JSONArray) node.get("statements");
		if(statementArr != null) {
			for(Object statementO : statementArr ) {
				JSONObject statement = (JSONObject) statementO;
				if(statement.get("nodeType").equals("PlaceholderStatement")) {
					this.statements.add(new PlaceholderStatement(statement));
				}else if(statement.get("nodeType").equals("IfStatement")) {
					this.statements.add(new IfStatement(statement));
				}else if(statement.get("nodeType").equals("EmitStatement")) {
					this.statements.add(new EmitStatement(statement));
				}else if(statement.get("nodeType").equals("ExpressionStatement")) {
					this.statements.add(new ExpressionStatement(statement));
				}else if(statement.get("nodeType").equals("ForStatement")) {
					this.statements.add(new ForStatement(statement));
				}else if(statement.get("nodeType").equals("Return")) {
					this.statements.add(new Return(statement));
				}else if(statement.get("nodeType").equals("VariableDeclarationStatement")) {
					this.statements.add(new VariableDeclarationStatement(statement));
				}else {
					System.out.println("=== Find new Value ===");
					System.out.println(statement.get("nodeType"));
					System.out.println("At id:"+statement.get("id"));
					System.out.println("At Parent NodeType:"+this.getNodeType());
				}
			}
		}
		this.keys = new HashSet<String>(node.keySet());
		System.out.println(id);
	}
	public long getId() {
		return id;
	}
	public String getNodeType() {
		return nodeType;
	}
	public String getSrc() {
		return src;
	}
	public Vector<Statement> getStatements() {
		return statements;
	}
	public HashSet<String> keySet(){
		return keys;
	}
}