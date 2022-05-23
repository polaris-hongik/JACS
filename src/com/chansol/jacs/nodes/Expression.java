package com.chansol.jacs.nodes;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Expression extends Node {
	protected HashMap<String,String> typeDescriptions = new HashMap<String,String>();
	protected Boolean isConstant;
	protected Boolean isLValue;
	protected Boolean isPure;
	protected Boolean lValueRequested;

	public Expression(JSONObject jsonObj) {
		super(jsonObj);
		JSONObject typeDescriptions = (JSONObject) jsonObj.get("typeDescriptions");
		this.typeDescriptions.put("typeIdentifier", typeDescriptions.get("typeIdentifier").toString());
		this.typeDescriptions.put("typeString", typeDescriptions.get("typeString").toString());
		this.isConstant = (Boolean)jsonObj.get("isConstant");
		this.isLValue = (Boolean)jsonObj.get("isConstant");
		this.isPure = (Boolean)jsonObj.get("isPure");
		this.lValueRequested = (Boolean)jsonObj.get("lValueRequested");
	}
	protected static Expression getExpression(JSONObject jsonObj) {
		Expression typedExpression = null;
		
		if(jsonObj.get("nodeType").equals("FunctionCall")) {
			typedExpression = new FunctionCall(jsonObj);
		}else if(jsonObj.get("nodeType").equals("ElementaryTypeNameExpression")) {
			typedExpression = new ElementaryTypeNameExpression(jsonObj);
		}else if(jsonObj.get("nodeType").equals("NewExpression")) {
			typedExpression = new NewExpression(jsonObj);
		}else if(jsonObj.get("nodeType").equals("MemberAccess")) {
			typedExpression = new MemberAccess((JSONObject) jsonObj);
		}else if(jsonObj.get("nodeType").equals("IndexAccess")) {
			typedExpression = new IndexAccess(jsonObj);
		}else if(jsonObj.get("nodeType").equals("TupleExpression")) {
			typedExpression = new TupleExpression(jsonObj);
		}else if(jsonObj.get("nodeType").equals("Conditional")) {
			typedExpression = new Conditional(jsonObj);
		}else if(jsonObj.get("nodeType").equals("FunctionCallOptions")) {
			typedExpression = new FunctionCallOptions(jsonObj);
		}else {
			if(Value.hasValue(jsonObj)) {
				typedExpression = Value.getValue(jsonObj);
			}else if(Operation.hasOperation(jsonObj)) {
				typedExpression = Operation.getOperation(jsonObj);
			}else {
				System.out.println("=== Find new Expression ===");
				System.out.println(jsonObj.get("nodeType"));
				System.out.println("At id: "+jsonObj.get("id"));
			}
		}
		return typedExpression;
	}
	
	//getter
	public HashMap<String,String> getTypeDescriptions() { return typeDescriptions; }
	public Boolean isConstant() { return isConstant; }
	public Boolean isLValue() {	return isLValue; }
	public Boolean isPure() { return isPure; }
	public Boolean islValueRequested() { return lValueRequested; }
}
