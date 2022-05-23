package com.chansol.jacs.nodes;

import org.json.simple.JSONObject;

public class EnumValue extends Value{
	private String name;
	private String nameLocation;
	
	public EnumValue(JSONObject jsonObj) {
		super(jsonObj);
		this.name = (String) jsonObj.get("name");
		this.nameLocation = (String) jsonObj.get("nameLocation");
	}

	//getter
	public String getName() { return name; }
	public String getNameLocation() { return nameLocation; }
}
