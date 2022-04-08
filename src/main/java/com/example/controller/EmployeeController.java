package com.example.controller;

import com.example.dto.EmployeeDto;
import com.example.service.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    //LIST
    // http://localhost:8080/api/v1/employees
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees(){
        return employeeServices.getAllEmployees();
    }

    // FIND
    // http://localhost:8080/api/v1/employees/1
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
       EmployeeDto employeeDto = employeeServices.getEmployeeById(id);
       return ResponseEntity.ok(employeeDto);
    }

    //SAVE
    // http://localhost:8080/api/v1/employees
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        employeeServices.createEmployee(employeeDto);
        return employeeDto;
    }

    //UPDATE
    // http://localhost:8080/api/v1/employees
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        employeeServices.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //DELETE
    // http://localhost:8080/api/v1/employees
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id) {
        employeeServices.deleteEmployee(id);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Employee Succesfully Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
