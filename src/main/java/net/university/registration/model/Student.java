package net.university.registration.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="student") // This annotation is only if the table has a different name to this class
@EntityListeners(AuditingEntityListener.class)
public class Student {
	
	@Id
	private String id;
	

	private String name;
	private String deptName;
	private int totCred;
	
	// Constructor
	public Student() {
		
	}
	// Constructor II
	public Student (String id, String name, String deptName, int totCred) {
		this.id = id;
		this.deptName = deptName;
		this.name = name;
		this.totCred = totCred; 
	}
	// Getters and Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public int getTotCred() {
		return totCred;
	}
	public void setTotCred(int totCred) {
		this.totCred = totCred;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", deptName=" + deptName + ", totCred=" + totCred + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getDeptName()=" + getDeptName() + ", getTotCred()="
				+ getTotCred() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
