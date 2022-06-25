package com.springbootdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbootdemo.entity.Employee;

@Repository
public class EmployeeDAOHibernteImpl implements EmployeeDAO {
	
	//define field for entity manager
	private EntityManager entityManager;
	
	//set up constructor injection
	@Autowired
	public EmployeeDAOHibernteImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		
		//get the currect hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		//create a query 
		org.hibernate.query.Query<Employee> theQuery=currentSession.createQuery("from Employee",Employee.class);
		
		//execute and get the result 
		List<Employee> employee = theQuery.getResultList();
		
		//return the result
		
		
		
		
		return employee;
	}

	@Override
	
	public Employee findById(int theId) {


		//get the currect hibernate session 
				Session currentSession = entityManager.unwrap(Session.class);
				
				Employee theEmployee = currentSession.get(Employee.class,theId);
				
				
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		//get the currect hibernate session 
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {


		//get the currect hibernate session 
				Session currentSession = entityManager.unwrap(Session.class);
				
				Query<?> theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
				theQuery.setParameter("employeeId", theId);
				
				theQuery.executeUpdate();
	}

}
