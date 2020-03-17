package net.university.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.university.registration.model.Department;;

@Repository
public interface DepartmentRepository extends JpaRepository<Department , String> {
	
	

}
