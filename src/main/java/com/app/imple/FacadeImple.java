package com.app.imple;

import java.util.Collection;

import com.app.Facade;
import com.dao.CoursesDAO;
import com.dao.CoursesDTO;
import com.dao.MaterialDAO;
import com.dao.MaterialDTO;
import com.dao.StudentDAO;
import com.dao.StudentDTO;
import com.dao.TeacherDAO;
import com.util.MyFactory;

public class FacadeImple implements Facade {

	public void addStudents() {
		//dao in this case it's an instance of SudentDAOImpleHibernate 
		StudentDAO dao = (StudentDAO) MyFactory.getObject("STUDENTS");		
		dao.addStudents();
	}

	public void searchStudents(int studentID) {
		//dao in this case it's an instance of SudentDAOImpleHibernate 
		StudentDAO dao = (StudentDAO) MyFactory.getObject("STUDENTS");		
		dao.searchStudents(studentID);
	}

	public void updateStudents(int studentID) {
		//dao in this case it's an instance of SudentDAOImpleHibernate 
		StudentDAO dao = (StudentDAO) MyFactory.getObject("STUDENTS");		
		dao.updateStudents(studentID);
	}

	public void showStudentsInCourse(int courseID) {
		StudentDAO dao = (StudentDAO) MyFactory.getObject("STUDENTS");
		dao.showStudentsInCourse(courseID);
	}

	public void addTeacher() {
		//dao in this case it's an instance of TeacherDAOImpleHibernate 
		TeacherDAO dao = (TeacherDAO) MyFactory.getObject("TEACHER");		
		dao.addTeacher();		
	}

	public void searchTeachercourses(int teacherID) {
		//dao in this case it's an instance of TeacherDAOImpleHibernate 
		TeacherDAO dao = (TeacherDAO) MyFactory.getObject("TEACHER");		
		dao.searchTeachercourses(teacherID);
	}

	public void updateTeacher(int teacherID) {
		//dao in this case it's an instance of TeacherDAOImpleHibernate 
		TeacherDAO dao = (TeacherDAO) MyFactory.getObject("TEACHER");		
		dao.updateTeacher(teacherID);
	}

	public void addcourse() {
		//dao in this case it's an instance of CoursesDAOImpleHibernate 
		CoursesDAO dao = (CoursesDAO) MyFactory.getObject("COURSES");		
		dao.addcourse();
	}

	public void searchCourseMaterial(int courseID) {
		//dao in this case it's an instance of CoursesDAOImpleHibernate 
		CoursesDAO dao = (CoursesDAO) MyFactory.getObject("COURSES");		
		dao.searchCourseMaterial(courseID);
	}

	public void showCourses() {
		//dao in this case it's an instance of CoursesDAOImpleHibernate 
		CoursesDAO dao = (CoursesDAO) MyFactory.getObject("COURSES");		
		dao.showCourses();
	}

	public void updatecourse(int courseID) {
		//dao in this case it's an instance of CoursesDAOImpleHibernate 
		CoursesDAO dao = (CoursesDAO) MyFactory.getObject("COURSES");		
		dao.updatecourse(courseID);
	}

	public void addmaterial() {
		MaterialDAO dao = (MaterialDAO) MyFactory.getObject("MATERIAL");
		dao.addmaterial();
	}

	public void assigteachercourse(int teacherID,int courseID) {
		TeacherDAO dao = (TeacherDAO) MyFactory.getObject("TEACHER");
		dao.assignTeacherCourse(teacherID,courseID);
		
	}

	public void subscribeStudentCourse(int courseID,int studentID,boolean ismaterial) {
		//dao in this case it's an instance of SudentDAOImpleHibernate 
		StudentDAO dao = (StudentDAO) MyFactory.getObject("STUDENTS");
		dao.subscribeStudentToCourse(courseID, studentID, ismaterial);
	}

	public void setcompletedtostudents(int courseID) {
		StudentDAO dao = (StudentDAO) MyFactory.getObject("STUDENTS");
		dao.setcompletedstudents(courseID);		
	}

}
