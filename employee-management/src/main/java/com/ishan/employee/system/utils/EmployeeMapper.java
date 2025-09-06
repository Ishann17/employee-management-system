package com.ishan.employee.system.utils;

import com.ishan.employee.system.dto.EmployeeResponseDto;
import com.ishan.employee.system.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponseDto entityToDto(Employee employee){
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        employeeResponseDto.setId(employee.getId());
        //Name should have a check for null.
        String lastName = employee.getLastName() != null ? employee.getLastName() : "";
        employeeResponseDto.setFullName(employee.getFirstName() + " " + lastName.trim());
        employeeResponseDto.setEmail(employee.getEmailId());
        employeeResponseDto.setEmployeeCode(employee.getEmployeeCode());
        employeeResponseDto.setSkills(employee.getSkills());
        employeeResponseDto.setPhoneNumber(employee.getPhoneNumber());
        employeeResponseDto.setCity(employee.getCity());
        employeeResponseDto.setActive(employee.isActive());
        employeeResponseDto.setDob(employee.getDob());

        return employeeResponseDto;
    }
}
