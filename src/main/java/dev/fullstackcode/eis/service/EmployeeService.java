package dev.fullstackcode.eis.service;

import dev.fullstackcode.eis.entity.Department;
import dev.fullstackcode.eis.entity.Employee;
import dev.fullstackcode.eis.entity.Gender;
import dev.fullstackcode.eis.repository.DepartmentRepository;
import dev.fullstackcode.eis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Integer id) {

        return employeeRepository.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmpDepartment(Integer emp_id , Integer dept_id) {

            Employee employee = employeeRepository.findById(emp_id).orElseThrow(() -> new RestClientException("Employee with id "+emp_id+" not found"));
            Department department = departmentRepository.findById(dept_id).orElseThrow(() -> new RestClientException("Department with id "+dept_id+" not found"));


            employee.setDepartment(department);
            employeeRepository.save(employee);

            return employee;

    }

    public List<Employee> findEmployeesByGender(Gender gender) {
        return employeeRepository.findByGender(gender);
    }

    public  List<Employee> searchEmployeesByGender(Gender gender) {
        return employeeRepository.searchByGender(gender);
    }

    public Employee updateEmployee(Employee employee) {
        Employee emp = employeeRepository.getById(employee.getId());
        if(emp == null) {
            throw new RestClientException("Employee with id "+employee.getId()+" not found");
        }
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
         employeeRepository.deleteById(id);
    }
}

