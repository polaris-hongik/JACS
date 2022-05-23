package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class InlineAssembly extends Statement {
	private String AST;
	private String evmVersion;
	private Vector<HashMap<String, Object>> externalReferences = new Vector<HashMap<String, Object>>();
	
	public InlineAssembly(JSONObject jsonObj) {
		super(jsonObj);
		this.AST = jsonObj.get("AST").toString();
		this.evmVersion = jsonObj.get("evmVersion").toString();
		
		for(Object externalReferences : (JSONArray) jsonObj.get("externalReferences")) {
			JSONObject externalReferenceJSON = (JSONObject) externalReferences;
			HashMap<String, Object> externalReference = new HashMap<String, Object>();
			for(Object key : externalReferenceJSON.keySet()) {
				externalReference.put(key.toString(), externalReferenceJSON.get(key.toString()));
			}
			this.externalReferences.add(externalReference);
		}
		
	}
	
	//getter
	public String getAST() { return AST; }
	public String getEvmVersion() { return evmVersion; }
	public Vector<HashMap<String, Object>> getExternalReferences() { return externalReferences; }
}
