package com.example.service.impl;

import com.example.dto.EmployeeDto;
import com.example.entity.EmployeeEntity;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> listEmployeeDto = new ArrayList<>();
        Iterable<EmployeeEntity> iterable = employeeRepository.findAll();
        for(EmployeeEntity temp : iterable){
            EmployeeDto dto = entityToDto(temp);
            listEmployeeDto.add(dto);
        }
        return listEmployeeDto;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = dtoToEntity(employeeDto);
        employeeRepository.save(employeeEntity);
        return employeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + id));

        EmployeeDto employeeDto = entityToDto(employeeEntity);
        return employeeDto;
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployee(Long id, EmployeeDto employeeDto) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + id));

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());

        employeeRepository.save(employee);

        EmployeeDto updatedDto = entityToDto(employee);
        return ResponseEntity.ok(updatedDto);
    }

    @Override
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: " + id));

        employeeRepository.delete(employeeEntity);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Employee Succesfully Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    //MODEL MAPPER
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto employeeDto = modelMapper.map(employeeEntity, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        return employeeEntity;
    }
}
