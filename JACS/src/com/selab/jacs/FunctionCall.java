package com.selab.jacs;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FunctionCall extends Value{
	private Vector<Value> arguments = new Vector<Value>();
	private Value expression;

    private String kind;
    private Vector<Object> names = new Vector<Object>(); // ÀÌ°Ô ¹¹Áö
    private boolean tryCall;


	public FunctionCall(JSONObject node) {
		super(node);
		this.kind = (String)node.get("kind");
		this.tryCall = (boolean)node.get("tryCall");
		
		JSONArray arguments = (JSONArray) node.get("arguments");
		for(Object argumentO : arguments) {
			JSONObject argument = (JSONObject) argumentO;
			if(argument.get("nodeType").equals("Literal")) {
				this.arguments.add(new Literal((JSONObject) argument));
			}else if(argument.get("nodeType").equals("UnaryOperation")) {
				this.arguments.add(new UnaryOperation((JSONObject) argument));
			}else if(argument.get("nodeType").equals("BinaryOperation")) {
				this.arguments.add(new BinaryOperation((JSONObject) argument));
			}else if(argument.get("nodeType").equals("Identifier")) {
				this.arguments.add(new Identifier((JSONObject) argument));
			}else if(argument.get("nodeType").equals("MemberAccess")) {
				this.arguments.add(new MemberAccess((JSONObject) argument));
			}else {
				System.out.println("=== Find new Value ===");
				System.out.println(argument.get("nodeType"));
				System.out.println("At id:"+argument.get("id"));
				System.out.println("At Parent NodeType:"+this.getNodeType());
			}
		}
		JSONObject expression = (JSONObject) node.get("expression");
		if(expression.get("nodeType").equals("Literal")) {
			this.expression = new Literal(expression);
		}else if(expression.get("nodeType").equals("UnaryOperation")) {
			this.expression = new UnaryOperation(expression);
		}else if(expression.get("nodeType").equals("BinaryOperation")) {
			this.expression = new BinaryOperation(expression);
		}else if(expression.get("nodeType").equals("Identifier")) {
			this.expression = new Identifier(expression);
		}else if(expression.get("nodeType").equals("MemberAccess")) {
			this.expression = new MemberAccess(expression);
		}else if(expression.get("nodeType").equals("ElementaryTypeNameExpression")) {
			this.expression = new ElementaryTypeNameExpression(expression);
		}else if(expression.get("nodeType").equals("NewExpression")) {
			this.expression = new NewExpression(expression);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(expression.get("nodeType"));
			System.out.println("At id:"+expression.get("id"));
			System.out.println("At Parent NodeType:"+this.getNodeType());
		}
		
		JSONArray names = (JSONArray)node.get("names");
		for(Object name : names) {
			System.out.println("overload is coming!");
			this.names.add(name);
		}
	}

	public Vector<Value> getArguments() {
		return arguments;
	}

	public Value getExpression() {
		return expression;
	}

	public String getKind() {
		return kind;
	}

	public Vector<Object> getNames() {
		return names;
	}

	public boolean isTryCall() {
		return tryCall;
	}
}
