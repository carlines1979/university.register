package net.university.registration.model;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="instructor") // This annotation is needed only if the table has a different name to this class
@EntityListeners(AuditingEntityListener.class)
public class Instructor {
	@Id
	private String id;
	

	private String name;
	private String deptName;
	private int salary;
	
	public Instructor(String id, String name, String deptName, int salary) {
		this.id = id;
		this.name = name;
		this.deptName = deptName;
		this.salary = salary; 
	}
	public Instructor() {
		
	}
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", name=" + name + ", deptName=" + deptName + ", salary=" + salary + "]";
	}
}

