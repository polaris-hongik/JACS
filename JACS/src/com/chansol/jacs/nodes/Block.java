package com.chansol.jacs.nodes;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Block extends Statement{
	private Vector<Statement> statements = new Vector<Statement>();
	
	public Block(JSONObject jsonObj) {
		super(jsonObj);
		
		if(jsonObj.get("statements") != null) {
			for(Object statement : (JSONArray) jsonObj.get("statements") ) {
				this.statements.add(Statement.getStatement((JSONObject)statement));
			}
		}
	}
	
	//getter
	public Vector<Statement> getStatements() { return statements; }
}