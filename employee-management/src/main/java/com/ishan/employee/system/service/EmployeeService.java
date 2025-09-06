package com.ishan.employee.system.service;

import com.ishan.employee.system.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

   Page<Employee> getEmployeesInPageable(int page, int size);
   List<Employee> getListOfEmployees();
   Employee saveEmployee(Employee employee);
   Employee updateEmployee(Long id, Employee employee);
   void deleteEmployee(Long id);
}
