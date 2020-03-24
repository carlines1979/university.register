package net.university.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.university.registration.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,String> {

	public List<Course> findByDeptName(String deptName); 
	
}
