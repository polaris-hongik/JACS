package com.selab.jacs;

import java.util.Arrays;
import java.util.HashSet;

import org.json.simple.JSONObject;

public class VariableDeclaration {
	private Boolean constant; // nullable 상수 여부
	private Boolean indexed; // nullable event의 파라미터만 가지는 값
	private String visibility;
	private String src;
	private Boolean stateVariable;
	private Object typeName;
	private String storageLocation;
	private String nameLocation;
	private String nodeType;
	private Long scope;
	private String name;
	private Long id;
	private String mutability;
	private TypeDescriptions typeDescriptions;
	private String functionSelector; //nullable
	private Value value; //nullable
	private Object overrides;//null 이외의 값이 나온다면 추가할 것
	private HashSet<String> keys = new HashSet<String>(Arrays.asList("constant","indexed","visibility","src","stateVariable","typeName","storageLocation","nameLocation","nodeType","scope","name","id","mutability","typeDescriptions","functionSelector","value","overrides"));
	
	public VariableDeclaration(JSONObject node) { //모든 요소 반영 이후 keys -> keySet() 후 null 과 비교하는 것들 include로 비교할 것
		if(node.get("constant") != null) {
			this.constant = (Boolean) node.get("constant");
		}
		if(node.get("indexed") != null) {
			this.indexed = (Boolean) node.get("indexed");
		}
		this.visibility = (String) node.get("visibility");
		this.src = (String) node.get("src");
		this.stateVariable= (Boolean) node.get("stateVariable");
		this.storageLocation = (String) node.get("storageLocation");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String)node.get("nodeType");
		this.scope = (Long) node.get("scope");
		this.name = (String) node.get("name");
		this.id = (Long) node.get("id");
		this.mutability = (String) node.get("mutability");
		this.typeDescriptions = new TypeDescriptions((JSONObject)node.get("typeDescriptions"));
		this.functionSelector = (String) node.get("functionSelector");
		this.overrides = node.get("overrides");
		if(overrides != null) {
			System.out.println("!key on VariableDeclaration -> override is not null!");
		}
		
		JSONObject typeNameO = (JSONObject)node.get("typeName");
		if(typeNameO.get("nodeType").equals("ElementaryTypeName")) {
			this.typeName = new ElementaryTypeName(typeNameO);
		}else if(typeNameO.get("nodeType").equals("Mapping")) {
			this.typeName = new Mapping(typeNameO);
		}else if(typeNameO.get("nodeType").equals("ArrayTypeName")) {
			this.typeName = new ArrayTypeName(typeNameO);
		}else if(typeNameO.get("nodeType").equals("UserDefinedTypeName")) {
			this.typeName = new UserDefinedTypeName(typeNameO);
		}else {
			System.out.println("=== Find new typeName ===");
			System.out.println(typeNameO.get("nodeType"));
			System.out.println("At id:"+typeNameO.get("id"));
		}
		
		if(node.get("value") != null) {
			JSONObject value = (JSONObject) node.get("value");
			if(value.get("nodeType").equals("Literal")) {
				this.value = new Literal(value);
			}else if(value.get("nodeType").equals("UnaryOperation")) {
				this.value = new UnaryOperation(value);
			}else if(value.get("nodeType").equals("BinaryOperation")) {
				this.value = new BinaryOperation(value);
			}else if(value.get("nodeType").equals("FunctionCall")) {
				this.value = new FunctionCall(value);
			}else {
				System.out.println("=== Find new Value ===");
				System.out.println(value.get("nodeType"));
				System.out.println("At id:"+value.get("id"));
			}
		}
		
		HashSet<String> keys = new HashSet<String>(node.keySet());
		keys.removeAll(this.keys);
		if(!keys.isEmpty()) {
			System.out.println("=== Find new Key(s) ===");
			for(String newKey : keys) {
				System.out.println(newKey);
			}
			System.out.println("At "+this.getClass()+" Class:id:"+this.id);
		}
		System.out.println(id);
	}

	public Boolean isConstant() {
		return constant;
	}

	public String getVisibility() {
		return visibility;
	}

	public String getSrc() {
		return src;
	}

	public Boolean isStateVariable() {
		return stateVariable;
	}

	public Object getTypeName() {
		return typeName;
	}

	public String getStorageLocation() {
		return storageLocation;
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

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public String getMutability() {
		return mutability;
	}

	public TypeDescriptions getTypeDescriptions() {
		return typeDescriptions;
	}

	public String getFunctionSelector() {
		return functionSelector;
	}

	public Value getValue() {
		return value;
	}

	public Boolean isIndexed() {
		return indexed;
	}
	
	public HashSet<String> keySet(){
		return keys;
	}
}
