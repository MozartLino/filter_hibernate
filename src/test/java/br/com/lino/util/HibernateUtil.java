package br.com.lino.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	public static Session session;
	private static SessionFactory sessionFactory;
	private static Transaction tx;

	public static Session getCurrentSession() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		return session;
	}

	public static void beginTransaction() {
		tx = session.beginTransaction();
	}

	public static void rollbackTransaction() {
		tx.rollback();
	}

	public static void closeSession() {
		session.close();
	}

}
