package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.model.Employee;
import com.employee.services.EmployeeService;

@Controller
public class WebAppController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "home";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable int id) {
		employeeService.deleteEmployeeByID(id);
		return "redirect:/";
	}

	@GetMapping("/insert")
	public String insertPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "insert";
	}

	@PostMapping("/insertEmployee")
	public ResponseEntity<?> insertEmployees(@ModelAttribute Employee employee) {
		ResponseEntity<?> addEmployee = employeeService.addEmployee(employee);
		return addEmployee;
	}

	@GetMapping("/edit")
	public String editPage(@RequestParam("id") int id, Model model) {
		ResponseEntity<?> response = employeeService.getEmployeeById(id);
		Employee employee = (Employee) response.getBody();

		if (response.getStatusCode() == HttpStatus.OK && employee != null) {
			model.addAttribute("employee", employee);
			return "edit";
		} else {
			return "error"; 
		}
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute Employee employee) {
		ResponseEntity<?> updateEmployee = employeeService.updateEmployee(employee);
		return "redirect:/"; 
	}

}
