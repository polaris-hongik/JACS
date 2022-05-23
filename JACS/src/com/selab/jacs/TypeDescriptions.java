package com.selab.jacs;

import org.json.simple.JSONObject;

public class TypeDescriptions { //��尡 �ƴմϴ�. ���� �Ͻʽÿ�, commontype/argumentTypes���ε� ��밡��
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
