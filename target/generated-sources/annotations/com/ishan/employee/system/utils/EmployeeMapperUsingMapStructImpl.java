package com.ishan.employee.system.utils;

import com.ishan.employee.system.dto.EmployeeResponseDto;
import com.ishan.employee.system.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-06T22:18:18+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperUsingMapStructImpl implements EmployeeMapperUsingMapStruct {

    @Override
    public EmployeeResponseDto convertEmployeeToEmployeeDTOResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        employeeResponseDto.setEmail( employee.getEmailId() );
        employeeResponseDto.setId( employee.getId() );
        employeeResponseDto.setEmployeeCode( employee.getEmployeeCode() );
        employeeResponseDto.setPhoneNumber( employee.getPhoneNumber() );
        employeeResponseDto.setCity( employee.getCity() );
        List<String> list = employee.getSkills();
        if ( list != null ) {
            employeeResponseDto.setSkills( new ArrayList<String>( list ) );
        }
        employeeResponseDto.setActive( employee.isActive() );
        employeeResponseDto.setDob( employee.getDob() );

        employeeResponseDto.setFullName( mapFullName(employee) );

        return employeeResponseDto;
    }
}
