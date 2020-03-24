package net.university.registration.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="course") // This annotation is only if the table has a different name to this class
@EntityListeners(AuditingEntityListener.class)
public class Course {
	@Id
	private String course_id;
	private String title;
	private String deptName;
	private int credits;
	
	public Course() {
		
	}
	
	public Course(String course_id, String title, String deptName, int credits) {
		this.course_id = course_id;
		this.title = title;
		this.deptName = deptName;
		this.credits = credits;
	}
	
	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Course [course_id=" + course_id + ", title=" + title + ", deptName=" + deptName + ", credits=" + credits
				+ "]";
	}
		

}
