package com.exl.services.employeesearch.forms;

import java.io.Serializable;

public class SearchForm implements Serializable{

private static final long serialVersionUID = 1L;

    private String searchText;
	
	
    private String startDate;

    private String endDate;

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
    
    


}
