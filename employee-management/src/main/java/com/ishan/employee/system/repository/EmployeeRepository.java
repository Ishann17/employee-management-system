package com.ishan.employee.system.repository;

import com.ishan.employee.system.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT employee_code FROM employees",
           nativeQuery = true)
    List<String> findAllEmployeeCode();

    boolean existsByEmailId(String email);
    boolean existsByPhoneNumber(String phNum);

}
