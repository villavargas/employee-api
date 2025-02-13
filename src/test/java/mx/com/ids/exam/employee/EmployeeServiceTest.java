package mx.com.ids.exam.employee;


import static org.apache.logging.log4j.LogManager.getLogger;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.employeeapi.exception.EmployeeNotFoundException;
import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import com.example.employeeapi.service.EmployeeService;
import com.example.employeeapi.service.impl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("Juan");
        employee1.setLastName("Gomez");
        employee1.setAge(30);
        employee1.setGender("Male");
        employee1.setBirthDate("1980-11-28");
        employee1.setPosition("Software Engineer");

        employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Ana");
        employee2.setLastName("Rodriguez");
        employee2.setAge(25);
        employee2.setGender("Female");
        employee2.setBirthDate("1980-11-28");
        employee2.setPosition("QA Analyst");
    }

    @Test
    void testGetAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees = employeeService.getAllEmployees();

        assertNotNull(employees);
        assertEquals(2, employees.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById_Found() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));

        Employee found = employeeService.getEmployeeById(1L);

        assertNotNull(found);
        assertEquals("Juan", found.getFirstName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(3L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeById(3L);
        });

        assertEquals("Employee not found with ID: 3", exception.getMessage());
    }

    @Test
    void testSaveEmployees() {
        when(employeeRepository.saveAll(anyList())).thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> savedEmployees = employeeService.saveEmployees(Arrays.asList(employee1, employee2));

        assertNotNull(savedEmployees);
        assertEquals(2, savedEmployees.size());
        verify(employeeRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testUpdateEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee1);

        Employee updated = new Employee();
        updated.setFirstName("Carlos");
        updated.setLastName("Gomez");
        updated.setAge(35);
        updated.setGender("Male");
        updated.setBirthDate("1980-11-28");
        updated.setPosition("Senior Engineer");

        Employee result = employeeService.updateEmployee(1L, updated);

        assertNotNull(result);
        assertEquals("Carlos", result.getFirstName());
        assertEquals(35, result.getAge());
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        doNothing().when(employeeRepository).delete(employee1);

        employeeService.deleteEmployee(1L);

        verify(employeeRepository, times(1)).delete(employee1);
    }
}