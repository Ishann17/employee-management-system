package com.ishan.employee.system.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private LocalDate dob;
    private String employeeCode;
    private String city;
}
