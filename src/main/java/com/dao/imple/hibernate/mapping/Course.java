package com.dao.imple.hibernate.mapping;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="curso")
public class Course 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	private String name;
	private String description;
	private double cost;
	private Date begindate;
	
	@OneToOne
	@JoinColumn(name="materialId")
	private Material material;
	
	@ManyToMany
	@JoinTable(
			name="alumnocurso",
			joinColumns= { @JoinColumn(name="courseId") },
			inverseJoinColumns= { @JoinColumn(name="studentId")})
	private Collection<Students> students;
	
	@ManyToMany
	@JoinTable(
			name="maestrocurso",
			joinColumns= { @JoinColumn(name="courseId") },
			inverseJoinColumns= { @JoinColumn(name="teacherId")})
	private Collection<Teacher> teachers;

	public int getCourseId() {
		return courseId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getBegindate() {
		return begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	public Collection<Students> getStudents() {
		return students;
	}

	public void setStudents(Collection<Students> students) {
		this.students = students;
	}

	public Collection<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Collection<Teacher> teachers) {
		this.teachers = teachers;
	}

}
