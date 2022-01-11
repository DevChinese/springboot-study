package com.hua.restfulstarter.service;

import com.hua.restfulstarter.domain.entity.Employee;
import com.hua.restfulstarter.domain.mapper.EmployeeMapper;
import com.hua.restfulstarter.exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper mapper;

    public Employee selectEmployeeById(Long id) {
        return mapper.selectEmployeeById(id);
    }

    public List<Employee> selectEmployees() {
        return mapper.selectEmployees();
    }

    public void addEmployee(Employee employee) {
        mapper.addEmployee(employee);
    }

    public void updateEmployee(Employee employee, Long id) {
        if (id != employee.getId()) {
            System.out.println("path variable 与 body 数据不一致！");
            return;
        }
        mapper.updateEmployee(employee);
    }

    public void deleteEmployee(Long id) {
        Employee employee = mapper.selectEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        mapper.deleteEmployee(id);
    }
}
