package com.ishan.employee.system.service;

import com.ishan.employee.system.exception.EmployeeNotFoundException;
import com.ishan.employee.system.model.Employee;
import com.ishan.employee.system.repository.EmployeeRepository;
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

    @Override
    public Employee saveEmployee(Employee employee) {
        boolean emailExists = employeeRepository.existsByEmailId(employee.getEmailId());
        boolean phoneNumberExists = employeeRepository.existsByPhoneNumber(employee.getPhoneNumber());
        if(emailExists){
            throw new IllegalArgumentException("Email already exists!");
        }
        if(phoneNumberExists){
            throw new IllegalArgumentException("Phone Number already exists!");
        }
        String empCode = generateEmployeeCode();
        employee.setEmployeeCode(generateEmployeeCode());
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
        existingEmployee.setActive(updatedEmployee.isActive());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee presentEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id : " + id + " not found!"));
        employeeRepository.delete(presentEmployee);
    }

    public String generateEmployeeCode(){
        List<String> existingCodes  = employeeRepository.findAllEmployeeCode();
        if (existingCodes == null || existingCodes.isEmpty()) {
            return "EMP-0001";
        }

        int max = 0;
        for(String code: existingCodes){
            if(code != null && code.startsWith("EMP-")) {
                try {
                    int num = Integer.parseInt(code.substring(4));
                    if(num > max) max = num;
                } catch (NumberFormatException e) {}
            }
        }
        int next = max + 1;
        return String.format("EMP-%04d", next);
    }

}
