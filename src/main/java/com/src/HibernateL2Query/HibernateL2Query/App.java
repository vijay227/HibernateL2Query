package com.src.HibernateL2Query.HibernateL2Query;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.src.HibernateL2Query.HibernateL2Query.Model.Alien;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Alien a = null;

		Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session1 = sf.openSession();
		session1.beginTransaction();

		Query q1 = session1.createQuery("from Alien where aid=101");
		q1.setCacheable(true);
		a = (Alien) q1.uniqueResult();
		System.out.println(a);

		session1.getTransaction().commit();
		session1.close();

		Session session2 = sf.openSession();
		session2.beginTransaction();

		Query q2 = session2.createQuery("from Alien where aid=101");
		q2.setCacheable(true);
		a = (Alien) q2.uniqueResult();
		System.out.println(a);

		session2.getTransaction().commit();
		session2.close();

	}
}
