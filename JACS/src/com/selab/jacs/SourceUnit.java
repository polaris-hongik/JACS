package com.selab.jacs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SourceUnit {
	private String absolutePath;
	private HashMap<String, Long> exportedSymbols;
	private long id;
	private String license;
	private String nodeType;
	private PragmaDirective pragmaDirective;
	private ImportDirective importDirective;
	private Vector<ContractDefinition> contractDefinitions = new Vector<ContractDefinition>();
	private HashSet<String> keys;
	
	@SuppressWarnings("unchecked")
	public SourceUnit(JSONObject jsonObj) {
		this.absolutePath = (String) jsonObj.get("absolutePath");
		this.exportedSymbols = (HashMap<String, Long>) jsonObj.get("exportedSymbols");
		this.id = (long) jsonObj.get("id");
		this.license = (String) jsonObj.get("license");
		this.nodeType = (String) jsonObj.get("nodeType");
		this.keys = new HashSet<String>(jsonObj.keySet());
		
		JSONArray nodesArray = (JSONArray) jsonObj.get("nodes");
		for(int i = 0; i < nodesArray.size(); i++) {
			JSONObject node = (JSONObject) nodesArray.get(i);
			if(node.get("nodeType").equals("PragmaDirective")) {
				this.pragmaDirective = new PragmaDirective(node);
			}else if(node.get("nodeType").equals("ImportDirective")) {
				this.importDirective = new ImportDirective(node);
			}else if(node.get("nodeType").equals("ContractDefinition")) {
				contractDefinitions.add(new ContractDefinition(node));
			}
		}
		System.out.println(id);
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public HashMap<String, Long> getExportedSymbols() {
		return exportedSymbols;
	}

	public long getId() {
		return id;
	}

	public String getLicense() {
		return license;
	}

	public String getNodeType() {
		return nodeType;
	}

	public PragmaDirective getPragmaDirective() {
		return pragmaDirective;
	}

	public Vector<ContractDefinition> getContractDefinitions() {
		return contractDefinitions;
	}
	
	public ImportDirective getImportDirective() {
		return importDirective;
	}

	public HashSet<String> keySet(){
		return keys;
	}
}
