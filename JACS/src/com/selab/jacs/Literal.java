package com.selab.jacs;

import org.json.simple.JSONObject;

public class Literal extends Value{ //nodeType 하나 더 추가시 이 클래스를 abstract class로 수정 후 사용할 예정 SubExpression도 함께
	private String kind; //Literal 에만 존재
	private String hexValue; //Literal 에만 존재
	private String value; //Literal 에만 존재
	
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
