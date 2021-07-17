package com.exl.services.employeesearch.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.exl.services.employeesearch.entity.Employees;
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
	public String searchPostPage(@ModelAttribute("employeeForms") SearchForm employeeForms,Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		SearchForm searchForm=new SearchForm();
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		searchForm.setStartDate(employeeForms.getStartDate()==null||employeeForms.getStartDate()==""?null:inputFormat.format(inFormat.parse(employeeForms.getStartDate())));
		searchForm.setEndDate(employeeForms.getEndDate()==null||employeeForms.getEndDate()==""?null:inputFormat.format(inFormat.parse(employeeForms.getEndDate())));
		searchForm.setSearchText(employeeForms.getSearchText());
		
		List<Employees> finalList = employeeService.fetchFilteredEmployee(searchForm);
		
		model.addAttribute("finalList", finalList);
		model.addAttribute("employeeForms", new SearchForm());
		
		return "employee-search";
		
		
		
		
		
	}
	
	
	
}
