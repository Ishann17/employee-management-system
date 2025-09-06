package com.ishan.employee.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email_id", unique = true, nullable = false)
    private String emailId;

    @Column(name="phone_number", unique = true)
    private String phoneNumber;

    @Column(name="salary")
    private Double salary;

    @Column(name="dateOfBirth")
    private LocalDate dob;

    // Human‑friendly code (e.g., EMP001, EMP002) — generation logic to follow
    @Column(name="employee_code", unique = true)
    private String employeeCode;

    @Column(name="city")
    private String city;

    @ElementCollection
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name  = "skill")
    private List<String> skills;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true; // default to true

    @Column(name="pan_card_num", unique = true)
    private String panCard;

    private String country;

    private String state;

    private boolean isProjectAssigned;

    private boolean isOnBench;

    @Column(name = "performance_rating")
    private Double performanceRating;

}
