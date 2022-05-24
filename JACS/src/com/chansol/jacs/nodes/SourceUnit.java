package com.chansol.jacs.nodes;

import java.util.HashMap;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class SourceUnit extends Node{
	private String absolutePath;
	private HashMap<String, Long> exportedSymbols;
	private String license;
	private PragmaDirective pragmaDirective;
	private Vector<ImportDirective> importDirectives = new Vector<ImportDirective>();
	private Vector<ContractDefinition> contractDefinitions = new Vector<ContractDefinition>();
	
	@SuppressWarnings("unchecked")
	public SourceUnit(JSONObject jsonObj) {
		super(jsonObj);
		this.absolutePath = (String) jsonObj.get("absolutePath");
		this.exportedSymbols = (HashMap<String, Long>) jsonObj.get("exportedSymbols");
		this.license = (String) jsonObj.get("license");
		
		JSONArray nodesArray = (JSONArray) jsonObj.get("nodes");
		for(int i = 0; i < nodesArray.size(); i++) {
			JSONObject node = (JSONObject) nodesArray.get(i);
			if(node.get("nodeType").equals("PragmaDirective")) {
				this.pragmaDirective = new PragmaDirective(node);
			}else if(node.get("nodeType").equals("ImportDirective")) {
				importDirectives.add(new ImportDirective(node));
			}else if(node.get("nodeType").equals("ContractDefinition")) {
				contractDefinitions.add(new ContractDefinition(node));
			}
		}
	}

	//getter
	public String getAbsolutePath() { return absolutePath; }
	public HashMap<String, Long> getExportedSymbols() {	return exportedSymbols;	}
	public String getLicense() { return license; }
	public PragmaDirective getPragmaDirective() { return pragmaDirective; }
	public Vector<ContractDefinition> getContractDefinitions() { return contractDefinitions; }
	public Vector<ImportDirective> getImportDirectives() { return importDirectives; }
}
