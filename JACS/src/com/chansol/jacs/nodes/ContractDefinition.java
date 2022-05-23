package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ContractDefinition extends Definition{
	private Boolean isFullyImplemented; //뭘 의미하는지 잘 모르겠음
	private String contractKind;
	private Vector<String> usedErrors = new Vector<String>(); //이게 뭐지
	private Boolean isAbstract;
	private int [] nodes = {0,0,0,0,0,0}; //노드들 갯수 알려주는 변수(var,fun,struct,modi,event,enum 순서)
	private Vector<VariableDeclaration> variableDeclarations = new Vector<VariableDeclaration>();
	private Vector<FunctionDefinition> functionDefinitions = new Vector<FunctionDefinition>();
	private Vector<StructDefinition> structDefinitions = new Vector<StructDefinition>();
	private Vector<ModifierDefinition> modifierDefinitions = new Vector<ModifierDefinition>();
	private Vector<EventDefinition> eventDefinitions = new Vector<EventDefinition>();
	private Vector<EnumDefinition> enumDefinitions = new Vector<EnumDefinition>();
	private Vector<Long> linearizedBaseContracts = new Vector<Long>();
	private Vector<Long> contractDependencies = new Vector<Long>(); //이게 뭐지 
	private long scope; // 상위 노트 ID
	private Vector<InheritanceSpecifier> baseContracts = new Vector<InheritanceSpecifier>();  //알듯 말듯 이게 뭐지
	private String canonicalName; //CNAME 레코드
	
	public ContractDefinition(JSONObject jsonObj) {
		super(jsonObj);
		this.isFullyImplemented = (Boolean) jsonObj.get("fullyImplemented");
		this.contractKind = (String) jsonObj.get("contractKind");
		this.isAbstract = (Boolean) jsonObj.get("abstract");
		this.scope = (long) jsonObj.get("scope");
		this.canonicalName = (String) jsonObj.get("canonicalName");
		if(this.canonicalName == null) {
			this.canonicalName = (String) jsonObj.get("name");
		}

		JSONArray usedErrors = (JSONArray) jsonObj.get("usedErrors");
		if(usedErrors != null) {
			for(Object usedError : usedErrors) {
				this.usedErrors.add((String)usedError);
			}
		}
		JSONArray nodesArr = (JSONArray)jsonObj.get("nodes");
		for(int i = 0; i < nodesArr.size(); i++) {
			JSONObject nodeObj = (JSONObject) nodesArr.get(i);
			if(nodeObj.get("nodeType").equals("VariableDeclaration")) {
				this.variableDeclarations.add(new VariableDeclaration(nodeObj));
				this.nodes[0]++;
			}else if(nodeObj.get("nodeType").equals("FunctionDefinition")) {
				this.functionDefinitions.add(new FunctionDefinition(nodeObj));
				this.nodes[1]++;
			}else if(nodeObj.get("nodeType").equals("StructDefinition")) {
				this.structDefinitions.add(new StructDefinition(nodeObj));
				this.nodes[2]++;
			}else if(nodeObj.get("nodeType").equals("ModifierDefinition")) {
				this.modifierDefinitions.add(new ModifierDefinition(nodeObj));
				this.nodes[3]++;
			}else if(nodeObj.get("nodeType").equals("EventDefinition")) {
				this.eventDefinitions.add(new EventDefinition(nodeObj));
				this.nodes[4]++;
			}else if(nodeObj.get("nodeType").equals("EnumDefinition")) {
				this.enumDefinitions.add(new EnumDefinition(nodeObj));
				this.nodes[5]++;
			}
		}
		for(Object linearizedBaseContract : (JSONArray) jsonObj.get("linearizedBaseContracts")) {
			this.linearizedBaseContracts.add((long)linearizedBaseContract);
		}
		for(Object contractDependencie : (JSONArray) jsonObj.get("contractDependencies")) {
			this.contractDependencies.add((long)contractDependencie);
		}
		for(Object baseContract : (JSONArray)jsonObj.get("baseContracts")) {
			this.baseContracts.add(new InheritanceSpecifier((JSONObject) baseContract));
		}
	}
	
	//getter
	public Boolean isFullyImplemented() { return isFullyImplemented; }
	public String getContractKind() { return contractKind; }
	public Vector<String> getUsedErrors() {	return usedErrors; }
	public Boolean isAbstract() { return isAbstract; }
	public int[] getNodes() { return nodes; }
	public Vector<VariableDeclaration> getVariableDeclarations() { return variableDeclarations; }
	public Vector<FunctionDefinition> getFunctionDefinitions() { return functionDefinitions; }
	public Vector<StructDefinition> getStructDefinitions() { return structDefinitions; }
	public Vector<ModifierDefinition> getModifierDefinitions() { return modifierDefinitions; }
	public Vector<EventDefinition> getEventDefinitions() { return eventDefinitions; }
	public Vector<EnumDefinition> getEnumDefinitions() { return enumDefinitions; }
	public Vector<Long> getLinearizedBaseContracts() { return linearizedBaseContracts; }
	public Vector<Long> getContractDependencies() { return contractDependencies; }
	public long getScope() { return scope; }
	public Vector<InheritanceSpecifier> getBaseContracts() { return baseContracts; }
	public String getCanonicalName() { return canonicalName; }
}
