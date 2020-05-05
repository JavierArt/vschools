package com.dao.imple.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.dao.StudentDAO;
import com.dao.imple.hibernate.mapping.Course;
import com.dao.imple.hibernate.mapping.StudentCourse;
import com.dao.imple.hibernate.mapping.Students;
import com.dao.imple.hibernate.mapping.Teacher;
import com.dao.imple.hibernate.mapping.TeachersCourse;

public class StudentDAOImpleHibernate implements StudentDAO{

	public void addStudents() 
	{			
		Session session = HibernateSessionFactory.getSession();
		Scanner scan = new Scanner(System.in);
		System.out.println("enter name");
		String name = scan.nextLine();
		System.out.println("enter addres");
		String address = scan.nextLine();
		System.out.println("enter phone");
		String phone = scan.nextLine();
		Transaction tx = null;
		try {
		tx = session.beginTransaction();
        
        //Add new student object
        Students stud = new Students();
        stud.setName(name);
        stud.setAddress(address);
        stud.setPhone(phone);
         
        //Save the student in database
        session.save(stud);
 
        //Commit the transaction
        tx.commit();
		}
		catch(Exception ex)
		{
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		}
		finally {
			session.close();
		}
    }

	public void searchStudents(int studentID) {
		Session session = HibernateSessionFactory.getSession();
		Students std = (Students) session.get(Students.class, studentID);
		if(std!=null)
		{
			System.out.println("--[Student]--");
			System.out.println(std.getStudentId());
			System.out.println(std.getName());
			System.out.println(std.getAddress());
			System.out.println(std.getPhone());
			
			System.out.println("--[COURSES]--");
			for(Course c: std.getCourses())
			{
				System.out.println("Name:" + c.getName() + "  Fecha inicio:" + c.getBegindate() + "  Costo:" + c.getCost());
			}
			session.close();
		}
		else {
			System.out.println("registrer not found");
			session.close();
		}
	}

	public void updateStudents(int StudentID) {
		Session session = HibernateSessionFactory.getSession();		
		//get register to modify
		Students std=(Students) session.get(Students.class, StudentID);
		if(std !=null)
		{
			Transaction tx = null;
			Scanner scan = new Scanner(System.in);
			System.out.println("enter name");
			String name = scan.nextLine();
			System.out.println("enter addres");
			String address = scan.nextLine();
			System.out.println("enter phone");
			String phone = scan.nextLine();

			try {
			tx = session.beginTransaction();
			//modify values
			std.setName(name);
			std.setAddress(address);
			std.setPhone(phone);

			//grabo las modificaciones
			session.update(std);
		
			//commiteo
			tx.commit();
			}
			catch(Exception ex)
			{
				if(tx!=null) tx.rollback();
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

	public void subscribeStudentToCourse(int courseID, int studentID,boolean ismaterial) {
		Session session = HibernateSessionFactory.getSession();		
		
		Students std=(Students) session.get(Students.class, studentID);
		Course cs = (Course) session.get(Course.class, courseID);
		if(std!=null && cs !=null)
		{
			Transaction tx = null;
			try {
				tx = session.beginTransaction();
		        //Save the assigments in database
				StudentCourse obj = new StudentCourse();
				obj.setCourseId(courseID);
				obj.setStudentId(studentID);
				obj.setCompleted(false);
				obj.setIsacquired(ismaterial);
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
		else if(std==null)
		{
			System.out.println("Student register not found");
			session.close();
		}
		else if(cs==null)
		{
			System.out.println("Course register not found");
			session.close();
		}

	}

	public void showStudentsInCourse(int courseID) {
		Session session = HibernateSessionFactory.getSession();
		Course co = (Course) session.get(Course.class,courseID);
		if(co!=null)
		{
			System.out.println("--[CURSO]--");
			System.out.println(co.getName());
			System.out.println(co.getDescription());
			System.out.println(co.getBegindate());
			
			System.out.println("--[STUDENTS]--");
			for(Students std: co.getStudents())
			{
				System.out.println("Name:" + std.getName());
			}
			session.close();
		}else
		{
			System.out.println("register not found");
			session.close();
		}
	}

	public void setcompletedstudents(int courseID) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tx =null;
		try {
			tx = session.beginTransaction();
			
			String hql = "UPDATE StudentCourse std SET std.completed = true WHERE std.courseId =:cid";		
			Query query = session.createQuery(hql);
			query.setParameter("cid", courseID);
			query.executeUpdate();
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
}
