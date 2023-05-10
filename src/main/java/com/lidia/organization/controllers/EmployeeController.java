package com.lidia.organization.controllers;

import com.lidia.organization.dto.EmployeeDto;
import com.lidia.organization.services.api.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> create(
            @Valid @RequestBody EmployeeDto employeeDto){
        employeeService.create(employeeDto);
        return ResponseEntity.ok("Employee registered successfully!");
    }

    @GetMapping("/read/{name}")
    public EmployeeDto read(
            @PathVariable String name){
        return employeeService.read(name);
    }

    @GetMapping("/read")
    public List<EmployeeDto> read(){
        return employeeService.read();
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeeDto update(
            @Valid @RequestBody EmployeeDto employeeDto){
        return employeeService.update(employeeDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(
            @PathVariable("id") Integer id){
        employeeService.delete(id);
    }

}
