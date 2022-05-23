package com.selab.jacs;

import org.json.simple.JSONObject;

public class TypeDescriptions { //노드가 아닙니다. 참고 하십시오, commontype/argumentTypes으로도 사용가능
	private String typeIdentifier;
	private String typeString;
	
	public TypeDescriptions(JSONObject node) {
		this.typeIdentifier = (String)node.get("typeIdentifier");
		this.typeString = (String)node.get("typeString");
	}

	public String getTypeIdentifier() {
		return typeIdentifier;
	}

	public String getTypeString() {
		return typeString;
	}

}
