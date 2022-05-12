package dev.fullstackcode.eis.service;


import dev.fullstackcode.eis.entity.Department;
import dev.fullstackcode.eis.entity.Employee;
import dev.fullstackcode.eis.repository.DepartmentRepository;
import dev.fullstackcode.eis.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer id) {
           return  departmentRepository.findById(id);

    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Department department) {

        departmentRepository.findById(department.getId()).orElseThrow(() -> new RestClientException("Department with id "+department.getId()+" not found"));
        return departmentRepository.save(department);
    }
}
