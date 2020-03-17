package net.university.registration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.university.registration.model.Instructor;
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String> {
	
	// Custom query to find instructors by department name
	public List<Instructor> findByDeptName(String deptName);
	
	// Custom query to find instructors with a salary greater than input parameter.
	@Query( value = "SELECT * FROM instructor i where i.salary >= :salary", nativeQuery = true)
	public List<Instructor> findGreaterThanSalary(@Param("salary") Integer salary); 

}

