package dev.fullstackcode.eis.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dev.fullstackcode.eis.validation.OnCreate;
import dev.fullstackcode.eis.validation.OnUpdate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Null(groups = {OnCreate.class})
    @NotNull(groups = OnUpdate.class)
    private Integer id;

    @NotBlank(message = "FirstName should not be blank")
    @Size(min = 3,message = "FirstName should be at least 3 chars")
    private String first_name;

    @NotBlank(message = "LastName should not be blank")
    @Size(min = 3,message = "LastName should be at least 3 chars")
    private String last_name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @PastOrPresent
    private LocalDate birth_date;

    @PastOrPresent
    private LocalDate hire_date;

    @NotBlank // (message = "Email should not be blank")
    @Email    // (message = "Not a valid email")
    private String email;

    @PositiveOrZero
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;



    public Employee() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }



}