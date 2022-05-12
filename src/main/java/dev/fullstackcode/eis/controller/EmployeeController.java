package dev.fullstackcode.eis.controller;


import dev.fullstackcode.eis.entity.Department;
import dev.fullstackcode.eis.entity.Employee;
import dev.fullstackcode.eis.entity.Gender;
import dev.fullstackcode.eis.service.EmployeeService;
import dev.fullstackcode.eis.validation.OnCreate;
import dev.fullstackcode.eis.validation.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.groups.Default;
import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping()
    public List<Employee> getEmployees() {
        return employeeService.getAllEmployees();
    }


    @GetMapping("/{id}")

    public Employee getEmployee(@PathVariable @Min(1) Integer id) {
        return employeeService.getEmployeeById(id).orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found with id : "+ id));
    }

//    @ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
//    @PostMapping
//    @Validated(CreateValidations.class)
//    public Employee createEmployee(@RequestBody @Valid Employee employee) {
//        return employeeService.createEmployee(employee);
//    }

    @ResponseStatus(HttpStatus.CREATED) // send HTTP 201 instead of 200 as new object created
    @PostMapping
    @Validated(OnCreate.class)
    public Employee createEmployee(@RequestBody @Valid Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping()
    public Employee updateEmployee(@RequestBody  @Validated({OnUpdate.class, Default.class}) Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping(value="/{id}")
    public  void deleteEmployee(@PathVariable("id") @Min(1) Integer id){
        employeeService.deleteEmployee(id);
    }

    @PatchMapping("/{empId}/dept/{deptId}")
    public Employee updateEmpDepartment(@PathVariable("empId") @Min(1) Integer emp_id , @PathVariable("deptId") @Min(1) Integer dept_id, @PathParam("id") @Min(1) String id) {
       return employeeService.updateEmpDepartment(emp_id,dept_id);
    }

    @PatchMapping("/{empId}")
    public Employee updateEmpDepartmentById(@PathVariable("empId") Integer emp_id , @RequestBody Department department) {
        return employeeService.updateEmpDepartment(emp_id,department.getId());
    }

    @GetMapping(value="/gender/{gender}")
    public List<Employee> getEmployeesByGender(@PathVariable String gender) {

        return employeeService.findEmployeesByGender(Gender.valueOf(gender));
    }



}

