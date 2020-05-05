package com.dao;

public interface StudentDAO {

	public void addStudents();
	public void searchStudents(int studentID);
	public void updateStudents(int studentID);
	public void subscribeStudentToCourse(int courseID,int studentID,boolean ismaterial);
	public void showStudentsInCourse(int courseID);
	public void setcompletedstudents(int courseID);
}
