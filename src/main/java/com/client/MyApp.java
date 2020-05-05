package com.client;

import java.util.Scanner;

import com.app.Facade;
import com.dao.imple.hibernate.CoursesDAOImpleHibernate;
import com.util.MyFactory;

public class MyApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		CoursesDAOImpleHibernate courobj = new  CoursesDAOImpleHibernate();
		Facade f = (Facade) MyFactory.getObject("FACADE");
		int opc;
		do
		{
			System.out.println("SCHOOL SYSTEM");
			System.out.println("1.Add students\n2.Show student and courses taken by the student\n3.Update Student\n4.show students in course");
			System.out.println("5.Add teacher\n6.Show teacher and courses given by teacher\n7.Update teacher");
			System.out.println("8.add course\n9.Show course material\n10.show avavilable courses\n11.update course");
			System.out.println("12.add material");
			System.out.println("13.assign a course to teacher\n14.Subscribe student to course");
			System.out.println("15.set completed course to all students in it");
			System.out.println("0.exit");
			opc=scan.nextInt();
			switch(opc)
			{
			case 1:f.addStudents();
				break;
			case 2:
				System.out.println("Enter student ID");
				int studentID = scan.nextInt();
				f.searchStudents(studentID);
				break;
			case 3:
				System.out.println("Enter student ID");
				int studentID2 = scan.nextInt();
				f.updateStudents(studentID2);
				break;
			case 4:
				System.out.println("Enter course ID");
				int courseId = scan.nextInt();
				f.showStudentsInCourse(courseId);
				break;
			case 5:f.addTeacher();
				break;
			case 6:
				System.out.println("Enter teacher ID");
				int teacherID = scan.nextInt();
				f.searchTeachercourses(teacherID);
				break;
			case 7:
				System.out.println("Enter teacher ID");
				int teacherID2 = scan.nextInt();
				f.updateTeacher(teacherID2);
				break;
			case 8:f.addcourse();
				break;
			case 9:
				System.out.println("Enter course ID");
				int courseID = scan.nextInt();
				f.searchCourseMaterial(courseID);
				break;
			case 10:
				f.showCourses();
				break;
			case 11:
				f.showCourses();
				int courseID2 = scan.nextInt();
				f.updatecourse(courseID2);
				break;
			case 12:f.addmaterial();
				break;
			case 13:
				System.out.println("enter teacher id");
				int teacherID3 = scan.nextInt();
				courobj.showCourses();
				System.out.println("select a course");
				int courseID3 = scan.nextInt();
				f.assigteachercourse(teacherID3, courseID3);
				break;
			case 14:
				System.out.println("enter student id");
				int studentID4 = scan.nextInt();
				courobj.showCourses();
				System.out.println("select a course");
				int courseID6 = scan.nextInt();
				scan.nextLine();
				System.out.println("El alumno ya compro su matertial:y/n");
				String m = scan.nextLine();
				boolean ismaterial = (m.equals("y"))? true:false;				
				f.subscribeStudentCourse(courseID6, studentID4, ismaterial);
				break;
			case 15:
				System.out.println("enter course id");
				int courseID7 = scan.nextInt();
				f.setcompletedtostudents(courseID7);
				break;
			case 0: 
				System.out.println("Exiting the system...");
				System.out.println("Exited the system");
			break;
			default:System.out.println("invalid option");
			break;
			}
		}while(opc!=0);
	}

}
