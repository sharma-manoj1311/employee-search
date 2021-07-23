package com.exl.services.employeesearch.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateSessionUtils {
	
	@Autowired
	private SessionFactory sessionFactory;
	private  final ThreadLocal<Session> session = new ThreadLocal<Session>();
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	public  Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public  void closeSession() throws HibernateException {

		Session s = (Session) session.get();
		session.set(null);
		if (s != null) {
			s.close();
		}
	}

	public  void beginTransaction() throws HibernateException {

		Session session = currentSession();
		if (session != null) {
			session.beginTransaction();
		}
	}

	public  void commitTransaction() throws HibernateException {

		Session session = currentSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			if (transaction != null) {
				transaction.commit();
			}
		}
	}

	public  void rollbackTransaction() {
		Session session = currentSession();
		if (session != null) {
			Transaction transaction = session.getTransaction();
			if (transaction != null)
				transaction.rollback();

		}
	}
}
