package com.lidia.organization.controllers;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.services.api.DepartamentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departament")
public class DepartamentController {

    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departmentService) {
        this.departamentService = departmentService;
    }

    @PostMapping("/krijo")
    @PreAuthorize("hasRole('ADMIN')")
    public void krijoDepartament(
            @RequestBody DepartamentDto departamentDto){
        departamentService.krijoDepartament(departamentDto);
    }

    @GetMapping("/kerko/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER')")
    public DepartamentDto kerkoDepartament(
            @PathVariable("id") Integer id){
        return departamentService.kerkoDepartament(id);
    }

    @GetMapping("/lexo")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartamentDto> lexoDepartamentet(){
        return departamentService.lexoDepartamentet();
    }

    @DeleteMapping("/fshi/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void fshiDepartament(
            @PathVariable("id") Integer id){
        departamentService.fshiDepartament(id);
    }

    @GetMapping("/lexo/punonjes")
    @PreAuthorize("hasAnyRole('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartamentDto> lexoDepartamentPunonjes(){
        return departamentService.lexoDepartamentPunonjes();
    }


}
