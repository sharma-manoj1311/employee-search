package com.exl.services.employeesearch.forms;



import java.util.HashMap;
import java.util.Map;

public class Search {

	
	private String value;
	private Boolean regex;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getValue() {
	return value;
	}

	public void setValue(String value) {
	this.value = value;
	}

	public Boolean getRegex() {
	return regex;
	}

	public void setRegex(Boolean regex) {
	this.regex = regex;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
}
