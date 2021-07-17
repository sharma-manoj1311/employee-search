package com.exl.services.employeesearch.repositoryImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exl.services.employeesearch.entity.Employees;
import com.exl.services.employeesearch.forms.SearchForm;
import com.exl.services.employeesearch.repository.EmployeeRepository;
@Repository
public class EmployeeRepositoryImpl  implements EmployeeRepository{
	@Autowired 
	private SessionFactory sessionFactory;
	@Override
	public List<Employees> fetchFilteredEmployee(SearchForm searchForm) throws Exception {
		
		try{
			
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Session session = sessionFactory.openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employees> query = builder.createQuery(Employees.class);
			EntityType<Employees> type = session.getMetamodel().entity(Employees.class);
			Root<Employees> root = query.from(Employees.class);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if (searchForm.getSearchText() != null && !searchForm.getSearchText().trim().equals("")) {
	            Predicate condition1 = builder.like(
				            builder.lower(
				                root.get(
				                    type.getDeclaredSingularAttribute("firstName", String.class)
				                )
				            ), "%" + searchForm.getSearchText().toLowerCase() + "%");
	            
	            
	            Predicate condition2 = builder.like(
			            builder.lower(
			                root.get(
			                    type.getDeclaredSingularAttribute("lastName", String.class)
			                )
			            ), "%" + searchForm.getSearchText().toLowerCase() + "%");
	            
	            criteria.add(condition1);
	            criteria.add(condition2);
	            
	        }
			
			if (searchForm.getStartDate() != null && !searchForm.getStartDate().equals("")) {
				
				Predicate startdateP = builder.equal(root.get(type.getDeclaredSingularAttribute("startDate", Date.class)), inputFormat.parse(searchForm.getStartDate()));
				 criteria.add(startdateP);
				
			}
			
			
			if (searchForm.getEndDate() != null && !searchForm.getEndDate().equals("")) {
				
				Predicate endDateP = builder.equal(root.get(type.getDeclaredSingularAttribute("endDate", Date.class)), inputFormat.parse(searchForm.getEndDate()));
				 criteria.add(endDateP);
				
			}
			if(criteria.size()!=0) {
				
				query.where(builder.or(criteria.toArray(new Predicate[criteria.size()])));
			}
			

			query.orderBy(builder.asc(root.get("employeeId")));
			return session.createQuery(query).getResultList();
		}
		catch(Exception e)
		{
			return null;
		}
		
		
		
	}

}
