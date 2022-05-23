package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class UserDefinedTypeName extends TypeName {
	private IdentifierPath pathNode;
	private long referencedDeclaration;
	private String name;
	private Object contractScope;
	
	public UserDefinedTypeName(JSONObject jsonObj) {
		super(jsonObj);
		if(jsonObj.get("pathNode") != null) {
			this.pathNode = new IdentifierPath((JSONObject) jsonObj.get("pathNode"));
		}
		this.referencedDeclaration = (long)jsonObj.get("referencedDeclaration");
		this.name = (String) jsonObj.get("name");
		
		//do not know yet
		this.contractScope = jsonObj.get("contractScope");
		if(contractScope != null) {
			System.out.println("!key on ContractDefinition -> contractScope is not null!");
		}
	}
	
	//getter
	public IdentifierPath getPathNode() { return pathNode; }
	public long getReferencedDeclaration() { return referencedDeclaration; }
	public String getName() { return this.name; }
}
