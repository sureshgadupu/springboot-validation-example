package dev.fullstackcode.eis.controller;

import dev.fullstackcode.eis.entity.Department;
import dev.fullstackcode.eis.exception.GenericException;
import dev.fullstackcode.eis.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private Validator validator;


    @GetMapping(value="/{id}")
    public Department getDepartmentById(@PathVariable Integer id)  {
        Optional<Department> dept = departmentService.getDepartmentById(id);
        return dept.orElseThrow( () -> new GenericException("Department not found"));
    }

    @GetMapping
    public List<Department> getDepartments() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        Set<ConstraintViolation<Department>> violations = validator.validate(department);
        if(!violations.isEmpty()) {
            throw  new ConstraintViolationException(violations);
        }
        return departmentService.createDepartment(department);
    }

    @PutMapping
    public Department updateDepartment(@RequestBody Department department) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Department>> violations = validator.validate(department);
        if(!violations.isEmpty()) {
            throw  new ConstraintViolationException(violations);
        }
        return departmentService.updateDepartment(department);
    }
}