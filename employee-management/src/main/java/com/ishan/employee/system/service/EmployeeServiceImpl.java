package com.ishan.employee.system.service;

import com.ishan.employee.system.exception.EmployeeNotFoundException;
import com.ishan.employee.system.model.Employee;
import com.ishan.employee.system.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> getEmployeesInPageable(int page, int size){
        log.trace("Entering getEmployeesInPageable(page={}, size={})", page, size);
        if(size < 1 || size > 100){
            throw new IllegalArgumentException("Size must be between 1 to 100.");
        }

        if(page < 0 ){
            throw new IllegalArgumentException("Page value cannot be in negative");
        }

        Pageable pageable = PageRequest.of(page, size);
       // log.trace("Exiting getEmployeesInPageable with {} employees", result.getTotalElements());
        return employeeRepository.findAll(pageable);
    }

    @Override
    public List<Employee> getListOfEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        boolean emailExists = employeeRepository.existsByEmailId(employee.getEmailId());
        boolean phoneNumberExists = employeeRepository.existsByPhoneNumber(employee.getPhoneNumber());
        if(emailExists){
            log.warn("Email already exists : {}" , employee.getEmailId());
            throw new IllegalArgumentException("Email already exists!");
        }
        if(phoneNumberExists){
            log.warn("Phone number already exists : {}" , employee.getPhoneNumber());
            throw new IllegalArgumentException("Phone Number already exists!");
        }
        String empCode = generateEmployeeCode();
        employee.setEmployeeCode(generateEmployeeCode());
        log.info("Employee added to the Database :: {}", employee);
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
        log.info("Employee with name : {} & id : {} is updated!",existingEmployee.getFirstName(), existingEmployee.getId());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee presentEmployee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with id : " + id + " not found!"));
        log.info("Employee with id : {} is deleted!", presentEmployee.getId());
        employeeRepository.delete(presentEmployee);
    }

    public String generateEmployeeCode(){

        log.info("Generate_Employee_Code() is called");
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
                } catch (NumberFormatException e) {
                    log.error("Invalid employee code format found in DB: {}", code, e);
                }
            }
        }
        int next = max + 1;
        log.debug("Employee Code {} is generated", next);
        return String.format("EMP-%04d", next);
    }

}
