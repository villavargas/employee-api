package com.example.employeeapi.service;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import com.example.employeeapi.exception.EmployeeNotFoundException;

import java.util.List;

@Service
public interface EmployeeService {
	

    public List<Employee> getAllEmployees();
    

    public Employee getEmployeeById(Long id) ;
    

    @Transactional
    public List<Employee> saveEmployees(List<Employee> employees);

    @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee);

    @Transactional
    public void deleteEmployee(Long id);
}
