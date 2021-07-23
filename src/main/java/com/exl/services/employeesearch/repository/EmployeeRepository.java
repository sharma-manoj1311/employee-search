package com.exl.services.employeesearch.repository;

import java.util.List;

import com.exl.services.employeesearch.entity.Employees;
import com.exl.services.employeesearch.forms.DataTableRequestDTO;
import com.exl.services.employeesearch.forms.SearchForm;

public interface EmployeeRepository {

	public List<Employees> fetchFilteredEmployee(DataTableRequestDTO criterias) throws Exception;
	
}
