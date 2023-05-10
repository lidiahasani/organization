package com.lidia.organization.controllers;

import com.lidia.organization.dto.DepartmentDto;
import com.lidia.organization.services.api.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public void create(
            @RequestBody DepartmentDto departmentDto){
        departmentService.create(departmentDto);
    }

    @GetMapping("/read/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER')")
    public DepartmentDto read(
            @PathVariable("id") Integer id){
        return departmentService.read(id);
    }

    @GetMapping("/read")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartmentDto> read(){
        return departmentService.read();
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER')")
    public void update(
            @RequestBody DepartmentDto departmentDto){
        departmentService.update(departmentDto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(
            @PathVariable("id") Integer id){
        departmentService.delete(id);
    }

}
