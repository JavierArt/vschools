package com.dao.imple.hibernate;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.TeacherDAO;
import com.dao.imple.hibernate.mapping.Course;
import com.dao.imple.hibernate.mapping.Teacher;
import com.dao.imple.hibernate.mapping.TeachersCourse;

public class TeacherDAOImpleHibernate implements TeacherDAO{

	public void addTeacher() {
		Scanner scan = new Scanner(System.in);
		System.out.println("enter name");
		String name = scan.nextLine();
		System.out.println("enter addres");
		String address = scan.nextLine();
		System.out.println("enter phone");
		String phone = scan.nextLine();
		System.out.println("enter email");
		String email = scan.nextLine();
		Session session = HibernateSessionFactory.getSession();
		Transaction tx = null;
		try {
	        tx = session.beginTransaction();
	        
	        //Add new student object
	        Teacher teach = new Teacher();
	        teach.setName(name);
	        teach.setAddress(address);
	        teach.setPhone(phone);
	        teach.setEmail(email);
	         
	        //Save the student in database
	        session.save(teach);
	 
	        //Commit the transaction
	        tx.commit();
		}
		catch(Exception ex)
		{
			if(tx!=null)tx.rollback();
			ex.printStackTrace();
		}
		finally {
        session.close();
		}
	}

	public void searchTeachercourses(int teacherID) {
		Session session = HibernateSessionFactory.getSession();
		Teacher teach = (Teacher) session.get(Teacher.class, teacherID);
		if(teach!=null)
		{
			System.out.println("--[Teacher]--");
			System.out.println(teach.getTeacherId());
			System.out.println(teach.getName());
			System.out.println(teach.getAddress());
			System.out.println(teach.getPhone());
			System.out.println(teach.getEmail());
			
			System.out.println("--[COURSES]--");
			for(Course c: teach.getCourses())
			{
				System.out.println("Name:" + c.getName() + "  Fecha inicio:" + c.getBegindate() + "  Costo:" + c.getCost());
			}		
			session.close();
		}else
		{
			System.out.println("register not found");
			session.close();
		}
	}

	public void updateTeacher(int teacherID) {
		Session session = HibernateSessionFactory.getSession();		
		//get register to modify
		Teacher tc=(Teacher) session.get(Teacher.class, teacherID);
		if(tc !=null)
		{
			Scanner scan = new Scanner(System.in);
			System.out.println("enter name");
			String name = scan.nextLine();
			System.out.println("enter addres");
			String address = scan.nextLine();
			System.out.println("enter phone");
			String phone = scan.nextLine();
			System.out.println("enter email");
			String email = scan.nextLine();

			Transaction tx = null;
			try {
			Transaction trx = session.beginTransaction();
			//modify values
			tc.setName(name);
			tc.setAddress(address);
			tc.setPhone(phone);
			tc.setEmail(email);

			//grabo las modificaciones
			session.update(tc);
		
			//commiteo
			trx.commit();
			}catch(Exception ex)
			{
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

	
	public void assignTeacherCourse(int teacherID,int courseID) {
		Session session = HibernateSessionFactory.getSession();		
				
		Teacher tc=(Teacher) session.get(Teacher.class, teacherID);
		Course cs = (Course) session.get(Course.class, courseID);
		if(tc!=null && cs !=null)
		{
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
		        //Save the assigments in database
				TeachersCourse obj = new TeachersCourse();
				obj.setCourseId(courseID);
				obj.setTeacherId(teacherID);
		        session.save(obj);
		 
		        //Commit the transaction
		        tx.commit();
			}catch(Exception ex){
				if(tx!=null)tx.rollback();
				ex.printStackTrace();				
			}
			finally {
				session.close();
			}
		}
		else if(tc==null)
		{
			System.out.println("Teacher register not found");
			session.close();
		}
		else if(cs==null)
		{
			System.out.println("course register not found");
			session.close();
		}
	}

}
