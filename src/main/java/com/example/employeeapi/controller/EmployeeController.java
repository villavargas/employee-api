package com.example.employeeapi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@Tag(name = "Employee API", description = "CRUD operations for employees")
class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

  
	@GetMapping(value = "/healthcheck")
	public String healthcheck(){
		logger.info("Status UP");
		return "Status UP";
	}
	

    @GetMapping
    @Operation(summary = "Get all employees", description = "Returns a list of all employees.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    
    @Operation(summary = "Get an employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    
    @PostMapping
    @Operation(summary = "Create multiple employees")
    @ApiResponse(responseCode = "201", description = "Employee created successfully")
    public ResponseEntity<List<Employee>> createEmployees(@Valid @RequestBody List<Employee> employees) {
        logger.info("Adding new or more employees: {}");
        return ResponseEntity.ok(employeeService.saveEmployees(employees));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee", description = "Updates an existing employee by ID.")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee", description = "Deletes an employee by ID.")
    @ApiResponse(responseCode = "204", description = "Employee deleted successfully")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
