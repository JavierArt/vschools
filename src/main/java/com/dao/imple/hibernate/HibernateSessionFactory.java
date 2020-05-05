package com.dao.imple.hibernate;

import java.io.File;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

	//nombre y ubicacion del archivo de configuracion
	public static String CONFIG_FILE="src\\main\\java\\com\\dao\\imple\\hibernate\\hibernate.cfg.xml";			
	private static SessionFactory sessionFactory = null;
	private static Session session = null;
	
	public static Session getSession()
	{
		if(sessionFactory == null || !session.isOpen() )
		{
			File f = new File(CONFIG_FILE);
			sessionFactory = new Configuration().configure(f).buildSessionFactory();			
							//.configure().buildSessionFactory();
			if(!sessionFactory.isOpen())
				System.out.println("parece que no hay factoria");
			session = sessionFactory.openSession();
			
		}
		return session;
	}
}
