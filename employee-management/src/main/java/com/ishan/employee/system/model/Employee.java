package com.ishan.employee.system.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Data
@Table(name = "employees")
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email_id")
    private String emailId;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="salary")
    private Double salary;
    @Column(name="dateOfBirth")
    private LocalDate dob;
    // Human‑friendly code (e.g., EMP001, EMP002) — generation logic to follow
    @Column(name="employee_code")
    private String employeeCode;
    @Column(name="city")
    private String city;
    @ElementCollection
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name  = "skills")
    private List<String> skills;

}
