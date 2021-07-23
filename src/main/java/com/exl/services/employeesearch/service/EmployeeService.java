package com.exl.services.employeesearch.service;

import com.exl.services.employeesearch.forms.DataTableRequestDTO;
import com.exl.services.employeesearch.forms.DatatablesDataSet;


public interface EmployeeService {

	public <T> DatatablesDataSet<T> getResultDataSet(DataTableRequestDTO criterias) throws Exception;
	
}
