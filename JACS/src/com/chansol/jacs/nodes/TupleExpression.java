package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TupleExpression extends Expression {
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
	private Vector<Expression> components = new Vector<Expression>();
	private boolean isInlineArray;
	
	public TupleExpression(JSONObject jsonObj) {
		super(jsonObj);
		
		this.isInlineArray = (boolean) jsonObj.get("isInlineArray");
		
		if(jsonObj.get("components") != null) {
			for(Object component : (JSONArray) jsonObj.get("components")) {
				if(component == null) {
					this.components.add(null);
				}else {
					this.components.add(Expression.getExpression((JSONObject) component));
				}
			}
		}
		
		if(jsonObj.get("argumentTypes") != null) {
			for(Object argumentTypeO: (JSONArray) jsonObj.get("argumentTypes")) {
				HashMap<String,String> typeDescriptions = new HashMap<String,String>();
				JSONObject argumentType = (JSONObject)argumentTypeO;
				typeDescriptions.put("typeIdentifier", (String)argumentType.get("typeIdentifier"));
				typeDescriptions.put("typeString", (String)argumentType.get("typeString"));
				this.argumentTypes.add(typeDescriptions);
			}
		}
	}

	//getter
	public Vector<HashMap<String, String>> getArgumentTypes() {	return argumentTypes; }
	public Vector<Expression> getComponents() {	return components; }
	public boolean getIsInlineArray() { return isInlineArray; }
}
