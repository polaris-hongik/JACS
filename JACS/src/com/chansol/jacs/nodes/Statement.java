package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class Statement extends Node{

    public Statement(JSONObject jsonObj){
    	super(jsonObj);
    	
    }
    
    protected static Statement getStatement(JSONObject jsonObj) {
    	Statement typedStatement = null;
		
		if(jsonObj.get("nodeType").equals("PlaceholderStatement")) {
			typedStatement = new PlaceholderStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("IfStatement")) {
			typedStatement = new IfStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("EmitStatement")) {
			typedStatement = new EmitStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("ExpressionStatement")) {
			typedStatement = new ExpressionStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("ForStatement")) {
			typedStatement = new ForStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("Return")) {
			typedStatement = new Return(jsonObj);
		}else if(jsonObj.get("nodeType").equals("VariableDeclarationStatement")) {
			typedStatement = new VariableDeclarationStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("WhileStatement")) {
			typedStatement = new WhileStatement(jsonObj);
		}else if(jsonObj.get("nodeType").equals("Block")) {
			typedStatement = new Block(jsonObj);
		}else if(jsonObj.get("nodeType").equals("InlineAssembly")) {
			typedStatement = new InlineAssembly(jsonObj);
		}else {
			System.out.println("=== Find new Statement ===");
			System.out.println(jsonObj.get("nodeType"));
			System.out.println("At id: "+jsonObj.get("id"));
		}
		return typedStatement;
	}
}
