package com.ishan.employee.system.controller;

import com.ishan.employee.system.dto.EmployeeResponseDto;
import com.ishan.employee.system.model.Employee;
import com.ishan.employee.system.service.EmployeeService;
import com.ishan.employee.system.utils.EmployeeMapperUsingMapStruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee/api")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeMapperUsingMapStruct mapperUsingMapStruct;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/pagedEmployees")
    public ResponseEntity<Page<EmployeeResponseDto>> getPaginatedEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Page<Employee> employeesInPageable = employeeService.getEmployeesInPageable(page, size);
        // The Page.map() method lets you convert each Employee inside the page to EmployeeResponseDto, so you get a Page of DTOs from a Page of entities.
        Page<EmployeeResponseDto> dtoPage = employeesInPageable.map(mapperUsingMapStruct::convertEmployeeToEmployeeDTOResponse);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees(){
        List<Employee> listOfEmployees = employeeService.getListOfEmployees();
        List<EmployeeResponseDto> dtoList = new ArrayList<>();
        dtoList = listOfEmployees.stream().map(mapperUsingMapStruct::convertEmployeeToEmployeeDTOResponse).toList();
        log.info("Controller getAllEmployees() method is called!");
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // Build the URI of the newly created resource
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();

        EmployeeResponseDto responseDto = mapperUsingMapStruct.convertEmployeeToEmployeeDTOResponse(savedEmployee);
        return ResponseEntity.ok().body(responseDto);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        EmployeeResponseDto responseDto = mapperUsingMapStruct.convertEmployeeToEmployeeDTOResponse(updatedEmployee);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
