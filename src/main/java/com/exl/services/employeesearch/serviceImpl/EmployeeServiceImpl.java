package com.exl.services.employeesearch.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exl.services.employeesearch.entity.Employees;
import com.exl.services.employeesearch.forms.SearchForm;
import com.exl.services.employeesearch.repository.EmployeeRepository;
import com.exl.services.employeesearch.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public List<Employees> fetchFilteredEmployee(SearchForm searchForm) throws Exception {
		
		return employeeRepository.fetchFilteredEmployee(searchForm);
	}

}
