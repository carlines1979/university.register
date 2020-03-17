package net.university.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.university.registration.model.Student;


@Repository 
public interface StudentRepository extends JpaRepository<Student, String>{
	
	// Custom query to find students by department name
	public List<Student> findByDeptName(String deptName);

	
	
}
