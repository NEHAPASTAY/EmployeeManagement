package com.employee.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeerepository;

	public ResponseEntity<?> addEmployee(Employee employee) {
		try {
			if (employeerepository.existsByEmail(employee.getEmail())) {
	            return ResponseEntity.badRequest().body("Employee with this email already exists");
	        }
			if (employee.getSalary() > 0) {
				Employee save = employeerepository.save(employee);
				return ResponseEntity.ok(save);
			} else {
				return ResponseEntity.ok("Salary should not be zero");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(e.toString());
		}

	}
	
	public ResponseEntity<?> getEmployeeById(int id) {
		Optional<Employee> optional = employeerepository.findById(id);
		try {
			if (optional.isPresent()) {
				Employee employee = optional.get();
				return ResponseEntity.ok(employee);
			} else {
				throw new Exception("Data Not Found by the given ID");
			}

		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	public ResponseEntity<?> getEmployeeByName(String name) {
		Optional<Employee> optional = employeerepository.findByName(name);
		try {
			if (optional.isPresent()) {
				Employee employee = optional.get();
				return ResponseEntity.ok(employee);
			} else {
				throw new Exception("Data Not Found by the given name");
			}

		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	public ResponseEntity<?> updateEmployee(Employee updatedEmployee) {
		Optional<Employee> optional = employeerepository.findById(updatedEmployee.getId());
		try {
			if (optional.isPresent()) {
//				Employee existingEmployee = optional.get();
//				existingEmployee.setName(updatedEmployee.getName());
				Employee savedEmployee = employeerepository.save(updatedEmployee);
				return ResponseEntity.ok(savedEmployee);

			} else {
				throw new Exception("No data found in the given id");
			}

		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}

	}

	public ResponseEntity<?> deleteEmployeeByID(int id) {
		Optional<Employee> existingEMP = employeerepository.findById(id);
		try {

			if (existingEMP.isPresent()) {
				employeerepository.deleteById(id);
				return ResponseEntity.ok("Employee deleted successfully");
			}
			else {
				throw new Exception("Data not Found");
			}

		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	public List<Employee> getAllEmployees() {
		return employeerepository.findAll();
	}
}
