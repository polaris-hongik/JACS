package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ArrayTypeName extends TypeName {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private TypeName baseType;
	private Object length;
	
	public ArrayTypeName(JSONObject jsonObj) {
		super(jsonObj);
		this.baseType = TypeName.getTypeName((JSONObject) jsonObj.get("baseType"));
		JSONArray argumentTypes = (JSONArray)jsonObj.get("argumentTypes");
		if(argumentTypes != null){
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
		
		//do not know yet
		this.length = jsonObj.get("length");
		if(length != null) {
			System.out.println("key 'length' is not null in Node ArrayTypeName");
		}
	}

	//getter
	public TypeName getBaseType() {	return baseType; }
	public Vector<HashMap<String,String>> getArgumentTypes() { return argumentTypes; }
}
