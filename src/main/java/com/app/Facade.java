package com.app;

public interface Facade {

	public void addStudents();
	public void searchStudents(int studentID);
	public void updateStudents(int studentID);
	public void subscribeStudentCourse(int courseID,int studentID,boolean ismaterial);
	public void showStudentsInCourse(int courseID);
	public void setcompletedtostudents(int courseID);
	
	public void addTeacher();
	public void searchTeachercourses(int teacherID);
	public void updateTeacher(int teacherID);
	public void assigteachercourse(int teacherID,int courseID);
	
	public void addcourse();
	public void searchCourseMaterial(int courseID);
	public void showCourses(); 
	public void updatecourse(int courseID);
	
	public void addmaterial();

}
