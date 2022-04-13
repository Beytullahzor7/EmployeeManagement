package com.example;

import com.example.entity.EmployeeEntity;
import com.example.repository.EmployeeRepository;
import com.example.test.TestCrud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class EmployeeManagementApplicationTests implements TestCrud {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @Override
    public void testCreate() {
        EmployeeEntity employeeEntity = EmployeeEntity.builder().firstName("Beytullah Test").lastName("Zor Test").emailId("555").build();

        employeeRepository.save(employeeEntity);

        //Nesne null ise assertionsError return et
        assertNotNull(employeeRepository.findById(1L).get());
    }

    @Test
    @Override
    public void testList() {
        List<EmployeeEntity> entityList = employeeRepository.findAll();

        assertThat(entityList).size().isGreaterThan(0);
    }

    @Test
    @Override
    public void testFindById() {
        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();

        //Böyle bir kayıt var mı yok mu check et
        assertEquals("Beytullah Test", employeeEntity.getFirstName());
    }

    @Test
    @Override
    public void testUpdate() {
        EmployeeEntity employeeEntity = employeeRepository.findById(1L).get();
        employeeEntity.setFirstName("Beytullah55 Test");
        employeeRepository.save(employeeEntity);

        //Esit degilse bir sorun olmayacak ama esit ise exception fırlatsın
        assertNotEquals("Beytullah Test", employeeRepository.findById(1L).get().getFirstName());
    }

    @Test
    @Override
    public void testDelete() {
        employeeRepository.deleteById(1L);

        assertThat(employeeRepository.existsById(1L)).isFalse();
    }
}
