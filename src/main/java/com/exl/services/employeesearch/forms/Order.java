package com.exl.services.employeesearch.forms;



import java.util.HashMap;
import java.util.Map;

public class Order {

	
	private Integer column;
	private String dir;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Integer getColumn() {
	return column;
	}

	public void setColumn(Integer column) {
	this.column = column;
	}

	public String getDir() {
	return dir;
	}

	public void setDir(String dir) {
	this.dir = dir;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}

}
