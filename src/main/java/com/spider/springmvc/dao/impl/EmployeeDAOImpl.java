package com.spider.springmvc.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spider.springmvc.dao.EmployeeDAO;
import com.spider.springmvc.domain.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	@Autowired(required = false)
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void addEmployee(Employee p) {
		System.out.println("ADD In DAO");
		
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Employee saved successfully, Employee Details="+p);
	}

	public void updateEmployee(Employee p) {
		System.out.println("UPDATE- DAOImpl");
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Employee updated successfully, Employee Details="+p);
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployees() {
		System.out.println("In DAOImpl");
		
		Session session = this.sessionFactory.getCurrentSession();
		List<Employee> EmployeesList = session.createQuery("from Employee").list();
		return EmployeesList;
	}

	public Employee getEmployeeById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		logger.info("Employee loaded successfully, Employee details="+p);
		return p;
	}

	public void removeEmployee(int id) {
		System.out.println("DAO : Remove");
		
		Session session = this.sessionFactory.getCurrentSession();
		Employee p = (Employee) session.load(Employee.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Employee deleted successfully, Employee details="+p);
	}

}