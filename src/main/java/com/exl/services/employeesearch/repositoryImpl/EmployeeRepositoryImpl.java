package com.exl.services.employeesearch.repositoryImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.exl.services.employeesearch.entity.Employees;
import com.exl.services.employeesearch.forms.DataTableRequestDTO;
import com.exl.services.employeesearch.forms.SearchForm;
import com.exl.services.employeesearch.repository.EmployeeRepository;
import com.exl.services.employeesearch.utils.HibernateSessionUtils;
import com.library.common.StringHelper;

@Repository
public class EmployeeRepositoryImpl extends HibernateSessionUtils implements EmployeeRepository {

	@Override
	public List<Employees> fetchFilteredEmployee(DataTableRequestDTO criterias) throws Exception {

		try {

			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");

			Session session = currentSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employees> query = builder.createQuery(Employees.class);
			EntityType<Employees> type = session.getMetamodel().entity(Employees.class);
			Root<Employees> root = query.from(Employees.class);

			List<Predicate> criteria = new ArrayList<Predicate>();
			if (criterias.getSearchText() != null && !criterias.getSearchText().trim().equals("")) {
				Predicate condition1 = builder.like(
						builder.lower(root.get(type.getDeclaredSingularAttribute("firstName", String.class))),
						"%" + criterias.getSearchText().toLowerCase() + "%");

				condition1=builder.or(condition1,builder.like(
						builder.lower(root.get(type.getDeclaredSingularAttribute("lastName", String.class))),
						"%" + criterias.getSearchText().toLowerCase() + "%"));
				criteria.add(condition1);

			}

			if (criterias.getStartDate() != null && !criterias.getStartDate().equals("") && criterias.getEndDate() == null && criterias.getEndDate().equals("")) {

				Predicate startdateP = builder.equal(
						root.get(type.getDeclaredSingularAttribute("startDate", Date.class)),
						inputFormat.parse(criterias.getStartDate() == null || criterias.getStartDate() == "" ? null
								: inputFormat.format(inFormat.parse(criterias.getStartDate()))));
				criteria.add(startdateP);

			}

			if (criterias.getEndDate() != null && !criterias.getEndDate().equals("") && criterias.getStartDate() == null && criterias.getStartDate().equals("")) {

				Predicate endDateP = builder.equal(root.get(type.getDeclaredSingularAttribute("endDate", Date.class)),
						inputFormat.parse(criterias.getEndDate() == null || criterias.getEndDate() == "" ? null
								: inputFormat.format(inFormat.parse(criterias.getEndDate()))));
				criteria.add(endDateP);

			}
			if (criterias.getStartDate() != null && !criterias.getStartDate().equals("") && criterias.getEndDate() != null && !criterias.getEndDate().equals("")) {

				Predicate startdateP = builder.greaterThanOrEqualTo(
						root.get(type.getDeclaredSingularAttribute("startDate", Date.class)),
						inputFormat.parse(criterias.getStartDate() == null || criterias.getStartDate() == "" ? null
								: inputFormat.format(inFormat.parse(criterias.getStartDate()))));
				startdateP = builder.and(startdateP, builder.lessThanOrEqualTo(root.get(type.getDeclaredSingularAttribute("endDate", Date.class)),
						inputFormat.parse(criterias.getEndDate() == null || criterias.getEndDate() == "" ? null
								: inputFormat.format(inFormat.parse(criterias.getEndDate())))));
				criteria.add(startdateP);

			}
			
			if(criteria.size()!=0) {
				
				query.where(builder.and(criteria.toArray(new Predicate[criteria.size()])));

			}
			
			query.orderBy(builder.asc(root.get("employeeId")));

			return session.createQuery(query).getResultList();
		} catch (Exception e) {
			return new ArrayList<Employees>();
		}

	}

}
