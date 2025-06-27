package com.ishan.employee.system.service;

import com.ishan.employee.system.model.Employee;
import com.ishan.employee.system.repository.EmployeeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getListOfEmployees() {
        return employeeRepository.findAll();
    }
}
