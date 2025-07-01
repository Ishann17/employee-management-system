package com.ishan.employee.system.service;

import com.ishan.employee.system.exception.EmployeeNotFoundException;
import com.ishan.employee.system.model.Employee;
import com.ishan.employee.system.repository.EmployeeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getListOfEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id : " + id + " not found!"));
        // Only update allowed fields (NOT id or employeeCode)
        existingEmployee.setFirstName(updatedEmployee.getFirstName());
        existingEmployee.setLastName(updatedEmployee.getLastName());
        existingEmployee.setEmailId(updatedEmployee.getEmailId());
        existingEmployee.setPhoneNumber(updatedEmployee.getPhoneNumber());
        existingEmployee.setDob(updatedEmployee.getDob());
        existingEmployee.setCity(updatedEmployee.getCity());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setSkills(updatedEmployee.getSkills());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee presentEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id : " + id + " not found!"));
        employeeRepository.delete(presentEmployee);
    }

}
