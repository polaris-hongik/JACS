package com.selab.jacs;

import org.json.simple.JSONObject;

public class ArrayTypeName extends TypeName {
	private TypeName baseType;
	
	public ArrayTypeName(JSONObject node) {
		super(node);
		JSONObject baseType = (JSONObject) node.get("baseType");
		if(baseType.get("nodeType").equals("ElementaryTypeName")) {
			this.baseType = new ElementaryTypeName((JSONObject) baseType);
		}else if(baseType.get("nodeType").equals("UserDefinedTypeName")) {
			this.baseType = new UserDefinedTypeName((JSONObject) node.get("baseType"));
		}else {
			System.out.println("=== Find new TypeName ===");
			System.out.println(baseType.get("nodeType"));
			System.out.println("At id:"+baseType.get("id"));
			System.out.println("At Parent NodeType:"+this.getNodeType());
		}
	}

	public TypeName getBaseType() {
		return baseType;
	}
}
