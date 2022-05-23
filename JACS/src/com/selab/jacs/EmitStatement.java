package com.selab.jacs;

import java.util.Vector;

import org.json.simple.JSONObject;

public class EmitStatement extends Statement{
	private FunctionCall eventCall;

	public EmitStatement(JSONObject node) {
		super(node);
		//FunctionCall 만 있는지 확실치 않음
		JSONObject eventcall = (JSONObject)node.get("eventCall");
		if(eventcall.get("nodeType").equals("FunctionCall")) {
			this.eventCall = new FunctionCall(eventcall);
		}else {
			System.out.println("=== Find new eventCallType ===");
			System.out.println(eventcall.get("nodeType"));
			System.out.println("At id:"+eventcall.get("id"));
		}
	}
	public FunctionCall getEventCall() {
		return eventCall;
	}
}
