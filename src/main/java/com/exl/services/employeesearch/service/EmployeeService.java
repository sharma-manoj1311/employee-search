package com.exl.services.employeesearch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.exl.services.employeesearch.entity.Employees;
import com.exl.services.employeesearch.forms.SearchForm;


public interface EmployeeService {

	public List<Employees> fetchFilteredEmployee(SearchForm searchForm) throws Exception;
	
}
