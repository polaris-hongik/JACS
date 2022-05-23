package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ImportDirective extends Node{
	private String absolutePath;
	private String file;
	private String nameLocation;
	private Long scope;
	private Long sourceUnit;
	private Vector<Long> symbolAliases = new Vector<Long>();
    private String unitAlias;
    
    public ImportDirective(JSONObject jsonObj){
    	super(jsonObj);
    	this.absolutePath = (String)jsonObj.get("absolutePath");
    	this.file = (String)jsonObj.get("file");
    	this.nameLocation = (String)jsonObj.get("nameLocation");
    	this.scope = (Long)jsonObj.get("scope");
    	this.sourceUnit = (Long)jsonObj.get("sourceUnit");
    	this.unitAlias = (String)jsonObj.get("unitAlias");
    	
    	JSONArray symbolAliases = (JSONArray) jsonObj.get("symbolAliases");
    	for(Object symbolAliase : symbolAliases) {
    		this.symbolAliases.add((long)symbolAliase);
    	}
    }

    //getter
	public String getAbsolutePath() { return absolutePath; }
	public String getFile() { return file; }
	public String getNameLocation() { return nameLocation; }
	public Long getScope() { return scope; }
	public Long getSourceUnit() { return sourceUnit; }
	public Vector<Long> getSymbolAliases() { return symbolAliases; }
	public String getUnitAlias() { return unitAlias; }
}
