package com.ishan.employee.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {

    private Long id;
    private String fullName;
    private String employeeCode;
    private String email;
    private String phoneNumber;
    private String city;
    private List<String> skills;
    private boolean isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dob;

}
