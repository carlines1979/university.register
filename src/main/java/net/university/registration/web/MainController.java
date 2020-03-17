package net.university.registration.web;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.university.registration.model.Department;
import net.university.registration.model.Instructor;
import net.university.registration.model.Student;
import net.university.registration.repository.DepartmentRepository;
import net.university.registration.repository.InstructorRepository;
import net.university.registration.repository.StudentRepository;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    
    @Autowired StudentRepository studentrepo;
    @RequestMapping(value = { "/university/studentList" }, method = RequestMethod.GET)
	 public String studentList(Model model) {
	 
	        model.addAttribute("students", studentrepo.findAll());
	 
	        return "studentList";
	 }	 
    
    @Autowired InstructorRepository instructorrepo; 
    @RequestMapping(value = { "/university/instructorList" }, method = RequestMethod.GET)
	 public String instructortList(Model model) {
	 
	        model.addAttribute("instructors", instructorrepo.findAll());
	 
	        return "instructorList";
	 }
    
    @Autowired DepartmentRepository departmentrepo; 
    @RequestMapping(value = { "/university/departmentList" }, method = RequestMethod.GET)
	 public String departmentList(Model model) {
	 
	        model.addAttribute("department", departmentrepo.findAll());
	 
	        return "departmentList";
	 }
    
    	// search STUDENT by ID
 		 // Searching page
 		 @RequestMapping(value = { "/university/studentById" }, method = RequestMethod.GET)
 		    public String showStudentById(Model model) {
 		 
 			 	
 		        model.addAttribute("student", new Student());
 		 
 		        return "studentById";
 		    }
 		 
 		 // INPUT ID
 		 @RequestMapping(value = { "/university/studentById" },  method = RequestMethod.POST)
 		    public String fetchStudent(Model model, @ModelAttribute("student") Student student, @RequestParam("id")  String id,
 		    		RedirectAttributes redirectAttributes) {	 	
 		      
 		        if (id != null && id.length() > 0 ) {
 		        	// findById returns an OPTIONAL that must be unwrapped 
 		        	studentrepo.findById(id).ifPresent(st -> model.addAttribute("student", st));
 		        	redirectAttributes.addAttribute("id", id); 
 		            return "redirect:/university/student/{id}";
 		        }
 		 
 		        Object errorMessage = "Student ID is mandatory";
 				model.addAttribute("errorMessage", errorMessage );
 		        return "studentById";
 				
 		    }
 		 
 		 // Student Found
 		 @RequestMapping(value = { "/university/student/{id}" },  method = RequestMethod.GET)
 		    public String searchStudent(Model model, @PathVariable("id")  String id) {	 	
 			 	
 			 	boolean present = studentrepo.findById(id).isPresent();
 		        
 	        	if (present == false) {
 	        		Object errorMessage = "There is no Student associated to this ID:" + id;
 					model.addAttribute("errorMessage", errorMessage );
 	        	} else {
 		        	// findById returns an OPTIONAL that must be unwrapped 
 		        	studentrepo.findById(id).ifPresent(st -> model.addAttribute("students", st));  
 	        	}
 	        	return "student";
 		        
 		 }
 		 
 		 // DELETE Student
 		@RequestMapping(value = "/delete/", method = RequestMethod.GET)
 		public String delete(Model model, @RequestParam("id")  String id, RedirectAttributes redirectAttributes) {
 			boolean present = studentrepo.findById(id).isPresent(); 
 			if (present) {
 				studentrepo.deleteById(id);
 				return "redirect:/";
 			} else {
 				return "student" ; 
 			}
 		
 		}
 		
 		// ADD a student to the database
 		// Show the addStudent page
 		@RequestMapping(value= {"university/addStudent"}, method = RequestMethod.GET)
 		public String showAddStudent(Model model) {
 				
 			Student student = new Student();
 			model.addAttribute("student", student); 
 			model.addAttribute("department", departmentrepo.findAll());
 			return "addStudent";
 		}
 			 
 		// Add the student to the database
 		@RequestMapping(value = { "university/addStudent" }, method = RequestMethod.POST)
 		public String saveStudent(Model model,  @ModelAttribute("student") Student student) {
 			 
 				String id = student.getId();
 			    String name = student.getName();
 			    String deptName = student.getDeptName();
 			    int totCred = student.getTotCred(); 
 			 
 			     if (id != null && id.length() > 0 //
 			         && name != null && name.length() > 0 //
 			         && deptName != null && deptName.length() > 0 //
 			         && totCred >= 0) {
 			         	Student newStudent = new Student(id, name, deptName, totCred);
 			            studentrepo.save(newStudent); 
 			 
 			            return "redirect:/university/studentList";
 			     }
 			 
 			        Object errorMessage = "All fields are mantatory";
 					model.addAttribute("errorMessage", errorMessage );
 					model.addAttribute("department", departmentrepo.findAll());
 			        return "addStudent";
 			    }
 		
		 // search INSTRUCTOR by ID
		 // Searching page
		 @RequestMapping(value = { "/university/instructorById" }, method = RequestMethod.GET)
		    public String showInstructorById(Model model) {
		 
			 	
		        model.addAttribute("instructor", new Instructor());
		 
		        return "instructorById";
		    }
		 
		 // INPUT ID
		 @RequestMapping(value = { "/university/instructorById" },  method = RequestMethod.POST)
		    public String fetchInstructor(Model model, @ModelAttribute("instructor") Instructor instructor, @RequestParam("id")  String id,
		    		RedirectAttributes redirectAttributes) {	 	
		      
		        if (id != null && id.length() > 0 ) {
		        	// findById returns an OPTIONAL that must be unwrapped 
		        	instructorrepo.findById(id).ifPresent(ins -> model.addAttribute("instructors", ins));
		        	redirectAttributes.addAttribute("id", id); 
		            return "redirect:/university/instructor/{id}";
		        }
		 
		        Object errorMessage = "Instructor ID is mandatory";
				model.addAttribute("errorMessage", errorMessage );
		        return "instructorById";
				
		    }
		 
		 // Instructor Found
		 @RequestMapping(value = { "/university/instructor/{id}" },  method = RequestMethod.GET)
		    public String searchInstructor(Model model, @PathVariable("id")  String id) {	 	
		      
	        	
		        	boolean present = instructorrepo.findById(id).isPresent();
		        
		        	if (present == false) {
		        		Object errorMessage = "There is no instructor associated to this ID:" + id;
		        		System.out.println("PRESENT: " + present + errorMessage.toString()); 
						model.addAttribute("errorMessage", errorMessage );
		        	} else {
		        		// findById returns an OPTIONAL that must be unwrapped 
		        		instructorrepo.findById(id).ifPresent(ins -> model.addAttribute("instructors", ins));
		        	}
		            return "instructor";
		        
		 }
		 
		// search INSTRUCTOR by DEPARTMENT
			// Searching page
			@RequestMapping(value = { "/university/instructorByDepartment" }, method = RequestMethod.GET)
				public String showInstructorByDepartment(Model model) {
					 
							
					    model.addAttribute("instructor", new Instructor());
					    model.addAttribute("department", departmentrepo.findAll());
					    return "instructorByDepartment";
				}
					 
			// INPUT DEPARTMENT
			@RequestMapping(value = { "/university/instructorByDepartment" },  method = RequestMethod.POST)
				public String fetchInstructorDept(Model model, @ModelAttribute("instructor") Instructor instructor, @RequestParam("deptName")  String deptName,
					    		RedirectAttributes redirectAttributes) {	 	
					      
					        if (deptName != null && deptName.length() > 0 ) {
					        	// Custom query to find instructors by department name
					        	model.addAttribute("instructors", instructorrepo.findByDeptName(deptName));
					        	redirectAttributes.addAttribute("deptName", deptName); 
					            return "redirect:/university/instructors/{deptName}";
					        }
					 
					        Object errorMessage = "The department name is mandatory";
							model.addAttribute("errorMessage", errorMessage );
					        return "instructorByDepartment";
							
					    }
					 
					 // Instructor Found
					 @RequestMapping(value = { "/university/instructors/{deptName}" },  method = RequestMethod.GET)
					    public String searchInstructorDept(Model model, @PathVariable("deptName")  String deptName) {	 	
					      
				        	
					        	List<Instructor> ins = instructorrepo.findByDeptName(deptName);
					        	System.out.println("INSTRUCTORS BY DEPT: " + ins); 
					        	if (ins.isEmpty()) {
					        		Object errorMessage = "There are no instructors associated to this department:" + deptName;
									model.addAttribute("errorMessage", errorMessage );
					        	} else {
					        		// 
					        		model.addAttribute("instructors", instructorrepo.findByDeptName(deptName));
					        	}
					            return "instructor";
					        
					 }
		 
 		 // DELETE Instructor
 		@RequestMapping(value = "/deleteIns/", method = RequestMethod.GET)
 		public String deleteI(Model model, @RequestParam("id")  String id, RedirectAttributes redirectAttributes) {
 			boolean present = instructorrepo.findById(id).isPresent(); 
 			if (present) {
 				instructorrepo.deleteById(id);
 				return "redirect:/";
 			} else {
 				return "instructor" ; 
 			}
 		
 		}
 		
		 // ADD a instructor to the database
		 // Show the addInstructor page
		 @RequestMapping(value= { "university/addInstructor" }, method = RequestMethod.GET)
		 public String showAddInstructor(Model model) {
			
			 Instructor instructor = new Instructor();
			 model.addAttribute("instructor", instructor); 
			 model.addAttribute("department", departmentrepo.findAll());
			 return "addInstructor";
		 }
		 
		 // Add the instructor to the database
		 @RequestMapping(value = { "university/addInstructor" }, method = RequestMethod.POST)
		    public String saveInstructor(Model model, //
		            @ModelAttribute("instructor") Instructor instructor) {
		 
		        String id = instructor.getId();
		        String name = instructor.getName();
		        String deptName = instructor.getDeptName();
		        int salary = instructor.getSalary(); 
		 
		        if (id != null && id.length() > 0 //
		                && name != null && name.length() > 0 //
		                && deptName != null && deptName.length() > 0 //
		                && salary >= 0) {
		            Instructor newInstructor = new Instructor(id, name, deptName, salary);
		            instructorrepo.save(newInstructor); 
		 
		            return "redirect:/university/instructorList";
		        }
		 
		        Object errorMessage = "All fields are mantatory";
				model.addAttribute("errorMessage", errorMessage );
				model.addAttribute("department", departmentrepo.findAll());
		        return "addInstructor";
		    }
		 
		 
		 // ADD a department to the database
		 // Show the addDepartment page
		 @RequestMapping(value= { "university/addDepartment" }, method = RequestMethod.GET)
		 public String showAddDepartment(Model model) {
			
			 Department department = new Department();
			 System.out.println("DEPARTMENT: " + department.toString());
			 model.addAttribute("department", department); 
			 return "addDepartment";
		 }
		 
		 // Add the department to the database
		 @RequestMapping(value = { "university/addDepartment" }, method = RequestMethod.POST)
		    public String saveDepartment(Model model, //
		            @ModelAttribute("department") Department department,
		            @RequestParam("deptName")  String deptName) {
			 
		        //String deptName = department.getDeptName();
		        System.out.println("DEPARTMENT NAME: " + deptName); 
		        String building = department.getBuilding();	 
		        System.out.println("BUILDING: " + building); 
		        int budget = department.getBudget(); 
		 
		        if (deptName != null && deptName.length() > 0 //
		                && building != null && building.length() > 0 //
		                && budget >= 0) {
		            Department newDepartment = new Department(deptName, building, budget);
		            departmentrepo.save(newDepartment);
		 
		            return "redirect:/university/departmentList";
		        }
		 
		        Object errorMessage = "All fields are mantatory";
				model.addAttribute("errorMessage", errorMessage );				
		        return "addDepartment";
		    }

		 
		 
}
