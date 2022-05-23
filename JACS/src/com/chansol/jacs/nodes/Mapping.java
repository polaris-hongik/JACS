package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class Mapping extends TypeName{
	private TypeName keyType;
	private TypeName valueType;
	
	public Mapping(JSONObject jsonObj) {
		super(jsonObj);
		this.keyType = TypeName.getTypeName((JSONObject) jsonObj.get("keyType"));
		this.valueType = TypeName.getTypeName((JSONObject) jsonObj.get("valueType"));
	}
	
	//getter
	public TypeName getKeyType() { return keyType; }
	public TypeName getValueType() { return valueType; }
}
