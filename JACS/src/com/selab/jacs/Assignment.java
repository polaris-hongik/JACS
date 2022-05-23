package com.selab.jacs;

import org.json.simple.JSONObject;

public class Assignment extends Operation {
	private Value leftHandSide;
	private Value rightHandSide; 
	
	public Assignment(JSONObject node) {
		super(node);
		JSONObject leftHandSide = (JSONObject) node.get("leftHandSide");
		if(leftHandSide.get("nodeType").equals("Literal")) {
			this.leftHandSide = new Literal(leftHandSide);
		}else if(leftHandSide.get("nodeType").equals("UnaryOperation")) {
			this.leftHandSide = new UnaryOperation(leftHandSide);
		}else if(leftHandSide.get("nodeType").equals("BinaryOperation")) {
			this.leftHandSide = new BinaryOperation(leftHandSide);
		}else if(leftHandSide.get("nodeType").equals("Identifier")) {
			this.leftHandSide = new Identifier(leftHandSide);
		}else if(leftHandSide.get("nodeType").equals("MemberAccess")) {
			this.leftHandSide = new MemberAccess(leftHandSide);
		}else if(leftHandSide.get("nodeType").equals("IndexAccess")) {
			this.leftHandSide = new IndexAccess(leftHandSide);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(leftHandSide.get("nodeType"));
			System.out.println("At id:"+leftHandSide.get("id"));
			System.out.println("At Parent NodeType:"+this.getNodeType());
		}

		JSONObject rightHandSide = (JSONObject) node.get("rightHandSide");
		if(rightHandSide.get("nodeType").equals("Literal")) {
			this.rightHandSide = new Literal(rightHandSide);
		}else if(rightHandSide.get("nodeType").equals("UnaryOperation")) {
			this.rightHandSide = new UnaryOperation(rightHandSide);
		}else if(rightHandSide.get("nodeType").equals("BinaryOperation")) {
			this.rightHandSide = new BinaryOperation(rightHandSide);
		}else if(rightHandSide.get("nodeType").equals("Identifier")) {
			this.rightHandSide = new Identifier(rightHandSide);
		}else if(rightHandSide.get("nodeType").equals("MemberAccess")) {
			this.rightHandSide = new MemberAccess(rightHandSide);
		}else if(rightHandSide.get("nodeType").equals("IndexAccess")) {
			this.rightHandSide = new IndexAccess(rightHandSide);
		}else {
			System.out.println("=== Find new Value ===");
			System.out.println(rightHandSide.get("nodeType"));
			System.out.println("At id:"+rightHandSide.get("id"));
			System.out.println("At Parent NodeType:"+this.getNodeType());
		}
	}
	public Value getLeftHandSide() {
		return leftHandSide;
	}

	public Value getRightHandSide() {
		return rightHandSide;
	}
}
