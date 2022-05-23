package com.selab.jacs;

import org.json.simple.JSONObject;

public class UserDefinedTypeName extends TypeName {
	private IdentifierPath pathNode;
	private long referencedDeclaration;
	public UserDefinedTypeName(JSONObject node) {
		super(node);
		if(node.get("pathNode") != null) {
			this.pathNode = new IdentifierPath((JSONObject) node.get("pathNode"));
		}
		this.referencedDeclaration = (long)node.get("referencedDeclaration");
	}
	public IdentifierPath getPathNode() {
		return pathNode;
	}
	public long getReferencedDeclaration() {
		return referencedDeclaration;
	}
}
