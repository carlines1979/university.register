package net.university.registration.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="department") // This annotation is needed only if the table has a different name to this class
@EntityListeners(AuditingEntityListener.class)
public class Department {
	
	@Id
	private String deptName;
	
	private String building;
	private int budget; 
	
	//Constructors
	public Department() {		
	}
	
	public Department(String deptName, String building, int budget) {
		this.deptName = deptName;
		this.building = building;
		this.budget = budget;
	}

	public String getDeptName() {
		return deptName;
	}
	public void setDeptname(String deptName) {
		this.deptName = deptName;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	
	@Override
	public String toString() {
		return "Department [deptname=" + deptName + ", building=" + building + ", budget=" + budget + ", getDeptname()="
				+ getDeptName() + ", getBuilding()=" + getBuilding() + ", getBudget()=" + getBudget() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}