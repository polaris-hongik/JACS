package com.selab.jacs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ContractDefinition {
	private Boolean isFullyImplemented; //뭘 의미하는지 잘 모르겠음
	private String contractKind;
	private String src;
	private Vector<String> usedErrors = new Vector<String>(); //이게 뭐지
	private Boolean isAbstract;
	private String nameLocation; //이거 뭘 뜻하는 거임
	private String nodeType;
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
	private String name;
	private long id;
	private Vector<InheritanceSpecifier> baseContracts = new Vector<InheritanceSpecifier>();  //알듯 말듯 이게 뭐지
	private String canonicalName; //CNAME 레코드
	private Object documentation; //null값 이외의 값이 나오면 추가
	private HashSet<String> keys = new HashSet<String>(Arrays.asList("fullyImplemented","contractKind","src","abstract","nameLocation","scope","name","id","nodeType",
																	"canonicalName","usedErrors","nodes","linearizedBaseContracts","contractDependencies","baseContracts","documentation"
																	));
	
	public ContractDefinition(JSONObject node) {
		this.isFullyImplemented = (Boolean) node.get("fullyImplemented");
		this.contractKind = (String) node.get("contractKind");
		this.src = (String) node.get("src");
		this.isAbstract = (Boolean) node.get("abstract");
		this.nameLocation = (String) node.get("nameLocation");
		this.nodeType = (String) node.get("nodeType");
		this.scope = (long) node.get("scope");
		this.name = (String) node.get("name");
		this.id = (long) node.get("id");
		this.canonicalName = (String) node.get("canonicalName");
		this.documentation = node.get("documentation");
		if(documentation != null) {
			System.out.println("!key on FunctionDefinition -> documentation is not null!");
		}
		
		JSONArray usedErrors = (JSONArray) node.get("usedErrors");
		if(usedErrors != null) {
			for(Object usedError : usedErrors) {
				this.usedErrors.add((String)usedError);
			}
		}
		
		JSONArray nodesArr = (JSONArray)node.get("nodes");
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
		JSONArray linearizedBaseContracts = (JSONArray) node.get("linearizedBaseContracts");
		for(Object linearizedBaseContract : linearizedBaseContracts) {
			this.linearizedBaseContracts.add((long)linearizedBaseContract);
		}
		JSONArray contractDependencies = (JSONArray) node.get("contractDependencies");
		for(Object contractDependencie : contractDependencies) {
			this.contractDependencies.add((long)contractDependencie);
		}
		JSONArray baseConArr = (JSONArray)node.get("baseContracts");
		for(Object baseContract:baseConArr) {
			this.baseContracts.add(new InheritanceSpecifier((JSONObject) baseContract));
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
	public Boolean isFullyImplemented() {
		return isFullyImplemented;
	}

	public String getContractKind() {
		return contractKind;
	}

	public String getSrc() {
		return src;
	}

	public Vector<String> getUsedErrors() {
		return usedErrors;
	}

	public Boolean isAbstract() {
		return isAbstract;
	}

	public String getNameLocation() {
		return nameLocation;
	}

	public String getNodeType() {
		return nodeType;
	}

	public int[] getNodes() {
		return nodes;
	}

	public Vector<VariableDeclaration> getVariableDeclarations() {
		return variableDeclarations;
	}

	public Vector<FunctionDefinition> getFunctionDefinitions() {
		return functionDefinitions;
	}

	public Vector<StructDefinition> getStructDefinitions() {
		return structDefinitions;
	}

	public Vector<ModifierDefinition> getModifierDefinitions() {
		return modifierDefinitions;
	}

	public Vector<EventDefinition> getEventDefinitions() {
		return eventDefinitions;
	}

	public Vector<EnumDefinition> getEnumDefinitions() {
		return enumDefinitions;
	}

	public Vector<Long> getLinearizedBaseContracts() {
		return linearizedBaseContracts;
	}

	public Vector<Long> getContractDependencies() {
		return contractDependencies;
	}

	public long getScope() {
		return scope;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public Vector<InheritanceSpecifier> getBaseContracts() {
		return baseContracts;
	}

	public String getCanonicalName() {
		return canonicalName;
	}
	
	public HashSet<String> keySet(){
		return keys;
	}
}
