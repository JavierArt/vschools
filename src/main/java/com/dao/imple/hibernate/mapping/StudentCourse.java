package com.dao.imple.hibernate.mapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alumnocurso")
public class StudentCourse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int studentId;	
	private int courseId;
	private boolean completed;
	private boolean isacquired;

	
	public int getId() {
		return id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public boolean isIsacquired() {
		return isacquired;
	}

	public void setIsacquired(boolean isacquired) {
		this.isacquired = isacquired;
	}
	
}
