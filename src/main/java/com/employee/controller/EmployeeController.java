package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.services.EmployeeService;

@RestController
//@RequestMapping("/emp")

public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;
	
	

	@PostMapping("/addEmployee")
	public @ResponseBody ResponseEntity<?> addEmployee(@RequestBody @Valid Employee employee) {
		return employeeservice.addEmployee(employee);
	}

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
		return employeeservice.getEmployeeById(id);
	}

	@GetMapping("/getEmployeeByName/{name}")
	public ResponseEntity<?> getEmployeeByName(@PathVariable String name) {
		return employeeservice.getEmployeeByName(name);
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		return employeeservice.updateEmployee(employee);
	}

	@DeleteMapping("/deleteEmployeeById/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		return employeeservice.deleteEmployeeByID(id);
	}

	@GetMapping("/getAll")
	public List<Employee> getAllEmployee() {
		return employeeservice.getAllEmployees();
	}
	

}
