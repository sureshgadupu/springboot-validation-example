package dev.fullstackcode.eis.repository;

import dev.fullstackcode.eis.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
