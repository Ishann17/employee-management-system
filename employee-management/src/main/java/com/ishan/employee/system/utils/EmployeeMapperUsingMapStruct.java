package com.ishan.employee.system.utils;

import com.ishan.employee.system.dto.EmployeeResponseDto;
import com.ishan.employee.system.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EmployeeMapperUsingMapStruct {

    @Mapping(source = "emailId", target = "email")
    @Mapping(target = "fullName", expression = "java(mapFullName(employee))")
    EmployeeResponseDto convertEmployeeToEmployeeDTOResponse(Employee employee);
    //Employee convertEmployeeDTOResponseToEmployee(EmployeeResponseDto dto);

    //Helper Method to Get Full Name
    default String mapFullName(Employee employee){
        return employee.getFirstName() + " " + employee.getLastName();
    }


}
