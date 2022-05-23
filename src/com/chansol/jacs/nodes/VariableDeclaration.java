package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class VariableDeclaration extends Definition{
	private Boolean constant; // nullable 상수 여부
	private Boolean indexed; // nullable event의 파라미터만 가지는 값
	private String visibility;
	private Boolean stateVariable;
	private TypeName typeName;
	private String storageLocation;
	private Long scope;
	private String mutability;
	private HashMap<String,String> typeDescriptions = new HashMap<String,String>();
	private String functionSelector; //nullable
	private Expression value; //nullable
	private OverrideSpecifier overrides;
	private Vector<Long> baseFunctions = new Vector<Long>();//nullable
	
	public VariableDeclaration(JSONObject jsonObj) {
		super(jsonObj);
		if(jsonObj.get("constant") != null) {
			this.constant = (Boolean) jsonObj.get("constant");
		}
		if(jsonObj.get("indexed") != null) {
			this.indexed = (Boolean) jsonObj.get("indexed");
		}
		this.visibility = (String) jsonObj.get("visibility");
		this.stateVariable= (Boolean) jsonObj.get("stateVariable");
		this.storageLocation = (String) jsonObj.get("storageLocation");
		this.scope = (Long) jsonObj.get("scope");
		this.mutability = (String) jsonObj.get("mutability");
		this.functionSelector = (String) jsonObj.get("functionSelector");
		this.typeName = TypeName.getTypeName((JSONObject)jsonObj.get("typeName"));
		if(jsonObj.get("overrides") != null) {
			this.overrides = new OverrideSpecifier((JSONObject) jsonObj.get("overrides"));
		}
		if(jsonObj.get("value") != null) {
			this.value = Expression.getExpression((JSONObject)jsonObj.get("value"));
		}
		
		JSONObject typeDescriptions = (JSONObject) jsonObj.get("typeDescriptions");
		this.typeDescriptions.put("typeIdentifier", typeDescriptions.get("typeIdentifier").toString());
		this.typeDescriptions.put("typeString", typeDescriptions.get("typeString").toString());
		if(jsonObj.get("baseFunctions") != null) {
			for (Object baseFunction : (JSONArray) jsonObj.get("baseFunctions")) {
				baseFunctions.add((long)baseFunction);
			}
		}
	}

	//getter
	public Boolean isConstant() { return constant; }
	public String getVisibility() {	return visibility; }
	public Boolean isStateVariable() { return stateVariable; }
	public TypeName getTypeName() {	return typeName; }
	public String getStorageLocation() { return storageLocation; }
	public Long getScope() { return scope; }
	public String getMutability() {	return mutability; }
	public HashMap<String,String> getTypeDescriptions() { return typeDescriptions; }
	public String getFunctionSelector() { return functionSelector; }
	public Expression getValue() { return value; }
	public Boolean isIndexed() { return indexed; }
	public OverrideSpecifier getOverrides() { return overrides; }
	public Vector<Long> getBaseFunctions() { return baseFunctions; }
}
