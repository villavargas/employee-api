package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();

        employee1 = new Employee();
        employee1.setFirstName("Juan");
        employee1.setLastName("Gomez");
        employee1.setAge(30);
        employee1.setGender("Male");
        employee1.setBirthDate("1980-11-28");
        employee1.setPosition("Software Engineer");

        employee2 = new Employee();
        employee2.setFirstName("Ana");
        employee2.setLastName("Rodriguez");
        employee2.setAge(25);
        employee2.setGender("Female");
        employee2.setBirthDate("1980-11-28");
        employee2.setPosition("QA Analyst");

        employeeRepository.saveAll(List.of(employee1, employee2));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Long employeeId = employee1.getId();

        mockMvc.perform(get("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Juan"));
    } 

    @Test
    void testCreateEmployees() throws Exception {
        Employee newEmployee = new Employee();
        newEmployee.setFirstName("Carlos");
        newEmployee.setLastName("Ramirez");
        newEmployee.setAge(40);
        newEmployee.setGender("Male");
        newEmployee.setBirthDate("1980-11-28");
        newEmployee.setPosition("Project Manager");

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(newEmployee))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Long employeeId = employee1.getId();
        employee1.setFirstName("UpdatedName");

        mockMvc.perform(put("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("UpdatedName"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Long employeeId = employee1.getId();

        mockMvc.perform(delete("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/employees/" + employeeId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
