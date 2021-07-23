package com.exl.services.employeesearch.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exl.services.employeesearch.entity.Employees;
import com.exl.services.employeesearch.forms.DataTableRequestDTO;
import com.exl.services.employeesearch.forms.DatatablesRecordsResponse;
import com.exl.services.employeesearch.forms.DatatablesRequestCriterias;
import com.exl.services.employeesearch.forms.SearchForm;
import com.exl.services.employeesearch.service.EmployeeService;

@Controller
public class SearchController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/employee-search", method = RequestMethod.GET)
	public String searchGetPage(@ModelAttribute("employeeForms") SearchForm employeeForms,Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return "employee-search";
		
	}
	
	
	
	
	@RequestMapping(value = "/employee-search", method = RequestMethod.POST)
    @ResponseBody
	public DatatablesRecordsResponse<Employees> findPCTeaAPIActionsWithDatatablesCriterias(HttpServletRequest request,
			HttpServletResponse httpResponse, @RequestBody DatatablesRequestCriterias datatablesRequestCriterias)
			throws Exception {

		DataTableRequestDTO criterias = DataTableRequestDTO.getRequestParamData(datatablesRequestCriterias);

		return DatatablesRecordsResponse.build(employeeService.getResultDataSet(criterias),
				criterias);

	}
	
	
	
}
