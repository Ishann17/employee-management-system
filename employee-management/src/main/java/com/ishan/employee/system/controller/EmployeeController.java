package com.ishan.employee.system.controller;

import com.ishan.employee.system.model.Employee;
import com.ishan.employee.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/allEmployees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> listOfEmployees = employeeService.getListOfEmployees();
        return ResponseEntity.ok(listOfEmployees);
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee savedEmployee = employeeService.saveEmployee(employee);

        // Build the URI of the newly created resource
       /* URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEmployee.getId())
                .toUri();*/

        return ResponseEntity.ok().body(savedEmployee);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
