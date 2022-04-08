package com.example.service;

import com.example.dto.EmployeeDto;
import com.example.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface EmployeeServices {

    //CRUD
    List<EmployeeDto> getAllEmployees();
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(Long id);
    ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto);
    ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id);

    //Model Mapper
    EmployeeDto entityToDto(EmployeeEntity employeeEntity);
    EmployeeEntity dtoToEntity(EmployeeDto employeeDto);
}
