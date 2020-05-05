package com.dao;

import java.util.Collection;

public interface TeacherDAO {

	public void addTeacher();
	public void searchTeachercourses(int teacherID);
	public void updateTeacher(int teacherID);
	public void assignTeacherCourse(int teacherID,int courseID);

}
