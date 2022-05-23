package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MemberAccess extends Expression{
	private Expression expression;
    private String memberName;
    private long referencedDeclaration;
	private Vector<HashMap<String,String>> argumentTypes = new Vector<HashMap<String,String>>();
    
    
    public MemberAccess(JSONObject jsonObj) {
		super(jsonObj);
		
		this.expression = Expression.getExpression((JSONObject) jsonObj.get("expression"));
		this.memberName = (String)jsonObj.get("memberName");
		if(jsonObj.get("referencedDeclaration") != null) {
			this.referencedDeclaration = (long) jsonObj.get("referencedDeclaration");
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
	public Expression getExpression() {	return expression; }
	public String getMemberName() {	return memberName; }
	public long getReferencedDeclaration() { return referencedDeclaration; }
	public Vector<HashMap<String, String>> getArgumentTypes() {	return argumentTypes; }
}
