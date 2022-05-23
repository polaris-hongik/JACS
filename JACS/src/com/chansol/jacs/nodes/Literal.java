package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Literal extends Value{
	private String kind;
	private String hexValue;
	private String value;
	private String subdenomination;
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();

	
	public Literal(JSONObject jsonObj) {
		super(jsonObj);
		if(jsonObj.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
		this.kind = (String) jsonObj.get("kind");
		this.hexValue = (String) jsonObj.get("hexValue");
		this.value = (String) jsonObj.get("value");
		
		//do not know yet
		if(subdenomination != null) {
			this.subdenomination = jsonObj.get("subdenomination").toString();
		}
	}

	//getter
	public String getKind() { return kind; }
	public String getHexValue() { return hexValue; }
	public String getValue() { return value; }
	public Vector<HashMap<String, String>> getArgumentTypes() {	return argumentTypes; }
	public String getSubdenomination() { return subdenomination; }
}
