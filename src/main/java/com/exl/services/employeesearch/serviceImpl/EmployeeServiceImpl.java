package com.exl.services.employeesearch.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exl.services.employeesearch.forms.DataTableRequestDTO;
import com.exl.services.employeesearch.forms.DatatablesDataSet;
import com.exl.services.employeesearch.forms.EmployeesResponseData;
import com.exl.services.employeesearch.repository.EmployeeRepository;
import com.exl.services.employeesearch.service.EmployeeService;
import com.library.common.StringHelper;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private int displayRecordsLength = 0;
	private Long totalCount = 0L;
	private Long filteredCount = 0L;
	public Long getFilteredCount() {
		return filteredCount;
	}

	public Long getTotalCount() {
		return totalCount;
	}
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public <T> DatatablesDataSet<T> getResultDataSet(DataTableRequestDTO criterias)
			throws Exception {
		List<T> rows = getRows(criterias);
		Long count = fetchTotalCount(criterias);
		Long countFiltered = fetchFilteredCount(criterias);
		return new DatatablesDataSet<T>(rows, count, countFiltered);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getRows(DataTableRequestDTO criterias) throws Exception {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<T> result = new ArrayList<T>();
		result=(List<T>) employeeRepository.fetchFilteredEmployee(criterias).stream().map(mapper->{
			EmployeesResponseData employeesResponseData=new EmployeesResponseData();
			employeesResponseData.setEmployeeId(mapper.getEmployeeId());
			employeesResponseData.setFirstName(mapper.getFirstName());
			employeesResponseData.setLastName(mapper.getLastName());
			employeesResponseData.setJobTittle(mapper.getJobTittle());
			employeesResponseData.setAge(mapper.getAge());
				employeesResponseData.setStartDate(mapper.getStartDate()==null?null:inFormat.format(mapper.getStartDate()));
				employeesResponseData.setEndDate(mapper.getEndDate()==null?null:inFormat.format(mapper.getEndDate()));
			
			
			return employeesResponseData;
		}).collect(Collectors.toList());
		
		
		displayRecordsLength = result.size();
		
		
		return result;
	}

	public <T> Long fetchFilteredCount(DataTableRequestDTO criterias) throws Exception {
		if (StringHelper.isEmpty(criterias.getSearch()) && (!criterias.hasOneFilteredColumn())) {
			filteredCount = totalCount;
			return totalCount;
		}

		if (criterias.getStart() == 0) {
			if (criterias.getLength() > displayRecordsLength) {
				filteredCount = (long) displayRecordsLength;
				return filteredCount;
			}
		}
		
		filteredCount = (long) employeeRepository.fetchFilteredEmployee(criterias).size();

		return filteredCount;
	}

	public <T> Long fetchTotalCount(DataTableRequestDTO criterias) throws Exception {
		totalCount =(long) employeeRepository.fetchFilteredEmployee(criterias).size();
		return totalCount;
	}

	

}
