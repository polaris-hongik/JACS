package com.selab.jacs;

import org.json.simple.JSONObject;

public class Literal extends Value{ //nodeType �ϳ� �� �߰��� �� Ŭ������ abstract class�� ���� �� ����� ���� SubExpression�� �Բ�
	private String kind; //Literal ���� ����
	private String hexValue; //Literal ���� ����
	private String value; //Literal ���� ����
	
	public Literal(JSONObject node) {
		super(node);
		this.kind = (String) node.get("kind");
		this.hexValue = (String) node.get("hexValue");
		this.value = (String) node.get("value");
	}

	public String getKind() {
		return kind;
	}

	public String getHexValue() {
		return hexValue;
	}

	public String getValue() {
		return value;
	}
}
