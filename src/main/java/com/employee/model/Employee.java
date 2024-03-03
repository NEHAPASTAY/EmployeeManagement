package com.employee.model;


import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Entity
public class Employee {
		
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", joiningDate=" + joiningDate
						+ ", Salary=" + Salary + "]";
			}

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		@NotBlank(message = "Name cannot be blank")
		private String name;
		@NotBlank(message = "Email cannot be blank")
		private String email;
		@Column(unique = false,nullable = true)
		private Date joiningDate;
		private int Salary;
		
		public int getId() {
			return id;
		}
		public Employee() {
			super();
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public Date getJoiningDate() {
			return joiningDate;
		}
		public void setJoiningDate(Date joiningDate) {
			this.joiningDate = joiningDate;
		}
		public int getSalary() {
			return Salary;
		}
		public void setSalary(int salary) {
			Salary = salary;
		}
		
		@PrePersist
		public void onCreate() {
			this.joiningDate = new Date();
			
			
		}
}
