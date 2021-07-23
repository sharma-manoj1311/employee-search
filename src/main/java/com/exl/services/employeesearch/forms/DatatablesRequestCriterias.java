package com.exl.services.employeesearch.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatatablesRequestCriterias {

	private Integer draw;
	private List<Column> columns = null;
	private List<Order> order = null;
	private Integer start;
	private Integer length;
	private Search_ search;
	private String searchText;
	private String startDate;
	private String endDate;

	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Search_ getSearch() {
		return search;
	}

	public void setSearch(Search_ search) {
		this.search = search;
	}

	

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
