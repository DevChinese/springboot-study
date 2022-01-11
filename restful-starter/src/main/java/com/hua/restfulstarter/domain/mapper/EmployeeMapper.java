package com.hua.restfulstarter.domain.mapper;

import com.hua.restfulstarter.domain.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    Employee selectEmployeeById(Long id);

    List<Employee> selectEmployees();

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Long id);
}
