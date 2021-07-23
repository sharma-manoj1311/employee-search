package com.exl.services.employeesearch.forms;



import java.util.HashMap;
import java.util.Map;

public class Column {

	
	private String data;
	private String name;
	private Boolean searchable;
	private Boolean orderable;
	private Search search;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getData() {
	return data;
	}

	public void setData(String data) {
	this.data = data;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public Boolean getSearchable() {
	return searchable;
	}

	public void setSearchable(Boolean searchable) {
	this.searchable = searchable;
	}

	public Boolean getOrderable() {
	return orderable;
	}

	public void setOrderable(Boolean orderable) {
	this.orderable = orderable;
	}

	public Search getSearch() {
	return search;
	}

	public void setSearch(Search search) {
	this.search = search;
	}

	public Map<String, Object> getAdditionalProperties() {
	return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
	this.additionalProperties.put(name, value);
	}
	
}
