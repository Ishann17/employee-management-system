package com.ishan.employee.system.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
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
    @Column(name="employee_code")
    private String employeeCode;

    @Column(name="city")
    private String city;

    @ElementCollection
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name  = "skills")
    private List<String> skills;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public Double getSalary() {
//        return salary;
//    }
//
//    public void setSalary(Double salary) {
//        this.salary = salary;
//    }
//
//    public LocalDate getDob() {
//        return dob;
//    }
//
//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }
//
//    public String getEmployeeCode() {
//        return employeeCode;
//    }
//
//    public void setEmployeeCode(String employeeCode) {
//        this.employeeCode = employeeCode;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public List<String> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(List<String> skills) {
//        this.skills = skills;
//    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", dob=" + dob +
                ", employeeCode='" + employeeCode + '\'' +
                ", city='" + city + '\'' +
                ", skills=" + skills +
                '}';
    }
}
