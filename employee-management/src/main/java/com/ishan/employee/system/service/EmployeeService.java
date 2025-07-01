package com.ishan.employee.system.service;

import com.ishan.employee.system.model.Employee;

import java.util.List;

public interface EmployeeService {

   List<Employee> getListOfEmployees();
   Employee saveEmployee(Employee employee);
   Employee updateEmployee(Long id, Employee employee);
   void deleteEmployee(Long id);
}
