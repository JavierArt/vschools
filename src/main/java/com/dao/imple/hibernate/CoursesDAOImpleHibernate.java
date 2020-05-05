package com.dao.imple.hibernate;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.CoursesDAO;
import com.dao.imple.hibernate.mapping.Course;
import com.dao.imple.hibernate.mapping.Material;
import com.util.DateToSQL;
import java.sql.Date;

public class CoursesDAOImpleHibernate implements CoursesDAO {
	
	public void addcourse() 
	{
		MaterialDAOImpleHibernate mater = new MaterialDAOImpleHibernate();
		Scanner scan = new Scanner(System.in);
		System.out.println("enter name");
		String name = scan.nextLine();
		System.out.println("enter description");
		String description = scan.nextLine();
		System.out.println("enter cost");
		int cost = scan.nextInt();
		Material mat =  mater.assignMaterial();
		System.out.println("enter begin date");
		Date beginDate = DateToSQL.transformDateToSQL();				
		
		Session session = HibernateSessionFactory.getSession();

		Transaction tx=null;
		try {
	        tx = session.beginTransaction();
	        
	        //Add new Course object
	        Course course = new Course();
	        course.setName(name);
	        course.setDescription(description);
	        course.setCost(cost);
	        course.setBegindate(beginDate);
	        course.setMaterial(mat);
	        
	        //Save the employee in database
	        session.save(course);
	        if(mat==null)
	        {
	        	tx.rollback();
	        }
	       	//Commit the transaction
        	tx.commit();
		}catch(Exception ex)
		{
			if(tx!=null)tx.rollback();
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
	}
		

	public void searchCourseMaterial(int courseID) 
	{
		Session session = HibernateSessionFactory.getSession();
		Course cs = (Course) session.get(Course.class, courseID);		
		if(cs!=null)
		{
			System.out.println("--[Curso]--");
			System.out.println(cs.getName());
			System.out.println(cs.getDescription());
			System.out.println(cs.getCost());
			System.out.println(cs.getBegindate());
			
			System.out.println("--[Material]--");
			Material mat = cs.getMaterial();
			System.out.println("detalles: "+mat.getDetails());
			System.out.println("costo: "+mat.getCost());
		}
		else
		{
			System.out.println("register not found");			
		}
	}

	public void showCourses() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "FROM Course c";
		Query query = session.createQuery(hql);
		
		List<Course> courses = query.list();
		if(!courses.isEmpty())
		{
			for(Course e: courses)
			{
				System.out.println(e.getCourseId() + " " +e.getName() + " " +e.getBegindate());
			}
		}
		session.close();
	}

	public void updatecourse(int courseID) {
		MaterialDAOImpleHibernate mater = new MaterialDAOImpleHibernate();
		Session session = HibernateSessionFactory.getSession();		
		//get register to modify
		Course cs=(Course) session.get(Course.class, courseID);
		if(cs !=null)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("enter name");
			String name = scan.nextLine();
			System.out.println("enter description");
			String description = scan.nextLine();
			System.out.println("enter cost");
			double costo = scan.nextDouble();
			Material mat =  mater.assignMaterial();
			System.out.println("enter begin date");
			Date d = DateToSQL.transformDateToSQL();
			Transaction tx=null;
			try {
				Transaction trx = session.beginTransaction();
				//modify values
				cs.setName(name);
				cs.setDescription(description);
				cs.setCost(costo);
				cs.setBegindate(d);
				
				//grabo las modificaciones
				session.update(cs);
		        if(mat==null)
		        {
		        	trx.rollback();
		        }
				//commiteo
				trx.commit();
			}catch(Exception ex) {
				if(tx!=null)tx.rollback();
				ex.printStackTrace();
			}
			finally {
				session.close();
			}
		}
		else
		{
			System.out.println("could not find student register");
			session.close();
		}
		
	}
}