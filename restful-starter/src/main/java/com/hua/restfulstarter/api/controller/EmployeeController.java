package com.hua.restfulstarter.api.controller;

import com.hua.restfulstarter.domain.entity.Employee;
import com.hua.restfulstarter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController  // @RestController指示每个方法返回的数据将直接写入响应体，而不是呈现模板。
@RequestMapping("payroll")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable Long id) {
        return employeeService.selectEmployeeById(id).toString();
    }

    @PostMapping("/employee")
    public void addEmployee(@RequestBody Employee newEmployee) {
        employeeService.addEmployee(newEmployee);
    }

    @GetMapping("/employee")
    public List<Employee> getEmployee() {
        return employeeService.selectEmployees();
    }

    @PutMapping("/employee/{id}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        employeeService.updateEmployee(employee, id);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }


}
