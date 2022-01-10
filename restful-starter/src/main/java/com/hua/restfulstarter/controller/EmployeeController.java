package com.hua.restfulstarter.controller;

import com.hua.restfulstarter.domain.entity.Employee;
import com.hua.restfulstarter.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.ImageProducer;
import java.util.List;

@RestController
@RequestMapping("payroll")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable int id) {
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


}
