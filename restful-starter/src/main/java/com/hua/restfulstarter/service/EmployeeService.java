package com.hua.restfulstarter.service;

import com.hua.restfulstarter.domain.entity.Employee;
import com.hua.restfulstarter.domain.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    public Employee selectEmployeeById(int id) {
        return mapper.selectEmployeeById(id);
    }

    public List<Employee> selectEmployees() {
        return mapper.selectEmployees();
    }

    public void addEmployee(Employee employee) {
        mapper.addEmployee(employee);
    }
}
