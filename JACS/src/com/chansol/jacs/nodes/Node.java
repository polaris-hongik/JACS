package com.chansol.jacs.nodes;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;
import java.lang.reflect.Field;

import org.json.simple.JSONObject;

public class Node {
	protected long id;
	protected String nodeType;
	protected String src; //nullable
	protected Long [] parentTrees;
	private static boolean debug = false;
	private static boolean refresh;
	private static HashMap<Long, Integer> ids = new HashMap<Long, Integer>();
	private static HashMap<Long, Node> idNodeMap = new HashMap<Long, Node>();
	private static HashMap<String,Vector<Node>> devideByTypes = new HashMap<String,Vector<Node>>();
	private static Vector<Long> dirFromRoot = new Vector<Long>();
	
	public Node(JSONObject jsonObj) {
		this.id = (long) jsonObj.get("id");
		this.nodeType = (String) jsonObj.get("nodeType");
		if(!this.nodeType.equals("SourceUnit")) {
			this.src = (String)jsonObj.get("src");
		}
		
		if(debug) {
			validateIds();
			validateKeys(jsonObj);
		}
		
		if(refresh) {
			devideByTypes.clear();
			idNodeMap.clear();
			refresh = false;
		}
		if(!devideByTypes.containsKey(this.nodeType)) {
			devideByTypes.put(this.nodeType, new Vector<Node>());
		}
		devideByTypes.get(this.nodeType).add(this);
		
		trimDir();
		dirFromRoot.add(this.id);
		
		parentTrees = Arrays.copyOf(dirFromRoot.toArray(), dirFromRoot.size(), Long[].class);
		
		if(!idNodeMap.keySet().contains(this.id)) {
			idNodeMap.put(this.id,this);
		}
	}
	
	public static void setDebugMode(boolean debug) { Node.debug = debug; }
	
	public static void getIdValidate(String rawAST) {
		Vector<Long> ids = new Vector<Long>();
		for(long id : Node.ids.keySet()) {
			ids.add(id);
		}
		Collections.sort(ids);
		
		long i = 1;
		for(long id : ids) {
			if(id != i) {
				if(rawAST.contains("\"id\": "+i+",")) {
					System.out.println("!!ID missing!!");
					System.out.println("expect : " + i + " -> get : " + id);
				}
				i = id;
			}
			if(Node.ids.get(i) > 1) {
				System.out.println("!!Multiple ID Detected!!");
				System.out.println("ID : " + i);
				System.out.println("count : " + Node.ids.get(i));
			}
			i++;
		}
		System.out.println("Id Validation finished for " + ids.get(ids.size()-1) + " Nodes");
		Node.ids.clear();
	}
	
	public static HashMap<String,Vector<Node>> getDevideByTypes(){
		refresh = true;
		return devideByTypes;
	}
	
	public static HashMap<Long,Node> getIdNodeMap() { 
		refresh = true;
		return idNodeMap;
	}
	
	private void validateIds() {
		if(ids.containsKey(this.id)) {
			ids.replace(this.id, ids.get(this.id)+1);
		}else {
			ids.put(this.id, 1);
		}
	}
	
	private void validateKeys(JSONObject jsonObj) {
		//예외목록{노드타입,키값}
		if(!this.getClass().getName().contains(nodeType)) {
			System.out.println("NodeType mismatch");
			System.out.println("current NodeType : "+ nodeType);
			System.out.println("current ID : " + id);
			System.out.println("current Class : "+this.getClass().getName());
		} else {
			String[][] exceptList= {{"SourceUnit","nodes"},
					{"PragmaDirective","literals"},
					{"ContractDefinition","abstract"},
					{"ContractDefinition","fullyImplemented"}}; 

			Vector<Class<?>> classes = new Vector<Class<?>>();
			Class<?> selectedClass = this.getClass();
			classes.add(selectedClass);
			while(!selectedClass.getGenericSuperclass().toString().contains("java")) {
				selectedClass = (Class<?>)selectedClass.getGenericSuperclass();
				classes.add(selectedClass);
			}
			
			for(Object key : jsonObj.keySet()) {
				boolean keyMissed = true;
				for(Class<?> nowClass : classes) {
					for(Field field : nowClass.getDeclaredFields()) {
						if(field.getName().equals(key.toString())) {
							keyMissed = false;
						}
					}
				}
				if(keyMissed) {
					boolean isException = false;
					for(String[] exception : exceptList) {
						if(this.nodeType.equals(exception[0]) && key.toString().equals(exception[1])) {
							isException = true;
						}
					}
					if(!isException) {
						System.out.println("Missing Key Alert!");
						System.out.println("id : "+this.id);
						System.out.println("nodeType : "+this.nodeType);
						System.out.println("class : "+this.getClass());
						System.out.println("missing key : "+key.toString());
					}
				}
			}
		}
	}
	
	private void trimDir() {
		int size = dirFromRoot.size();
		if(size != 0) {
			if(dirFromRoot.get(size-1) < this.id) {
				dirFromRoot.remove(size-1);
				trimDir();
			}
		}
	}
	
	//getter
	public long getId() { return id; }
	public String getNodeType() { return nodeType; }
	public String getSrc() { return src; }
	public Long[] getParentTrees() { return parentTrees; }
}
