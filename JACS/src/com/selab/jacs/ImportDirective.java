package com.selab.jacs;

import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ImportDirective {
	private String absolutePath;
	private String file;
	private Long id;
	private String nameLocation;
	private String nodeType;
	private Long scope;
	private Long sourceUnit;
	private String src;
	private Vector<Long> symbolAliases = new Vector<Long>();
    private String unitAlias;
    private HashSet<String> keys;
    
    public ImportDirective(JSONObject node){
    	this.absolutePath = (String)node.get("absolutePath");
    	this.file = (String)node.get("file");
    	this.id = (Long)node.get("id");
    	this.nameLocation = (String)node.get("nameLocation");
    	this.nodeType = (String)node.get("nodeType");
    	this.scope = (Long)node.get("scope");
    	this.sourceUnit = (Long)node.get("sourceUnit");
    	this.src = (String)node.get("src");
    	this.unitAlias = (String)node.get("unitAlias");
    	this.keys = new HashSet<String>(node.keySet());
    	
    	JSONArray symbolAliases = (JSONArray) node.get("symbolAliases");
    	for(Object symbolAliase : symbolAliases) {
    		this.symbolAliases.add((long)symbolAliase);
    	}
    	System.out.println(id);
    }

	public String getAbsolutePath() {
		return absolutePath;
	}

	public String getFile() {
		return file;
	}

	public Long getId() {
		return id;
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public String getNodeType() {
		return nodeType;
	}

	public Long getScope() {
		return scope;
	}

	public Long getSourceUnit() {
		return sourceUnit;
	}

	public String getSrc() {
		return src;
	}

	public Vector<Long> getSymbolAliases() {
		return symbolAliases;
	}

	public String getUnitAlias() {
		return unitAlias;
	}
	
	public HashSet<String> keySet(){
		return keys;
	}
}
