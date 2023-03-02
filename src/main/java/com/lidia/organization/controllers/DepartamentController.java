package com.lidia.organization.controllers;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.services.impl.DepartamentServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departament")
public class DepartamentController {

    private final DepartamentServiceImpl departamentService;

    public DepartamentController(DepartamentServiceImpl departmentService) {
        this.departamentService = departmentService;
    }

    @PostMapping("/krijo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void krijoDepartament(
            @RequestBody DepartamentDto departamentDto){
        departamentService.krijoDepartament(departamentDto);
    }

    @GetMapping("/kerko/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public DepartamentDto kerkoDepartament(
            @PathVariable("id") Integer id){
        return departamentService.kerkoDepartament(id);
    }

    @GetMapping("/lexo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartamentDto> lexoDepartamentet(){
        return departamentService.lexoDepartamentet();
    }

    @DeleteMapping("/fshi/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void fshiDepartament(
            @PathVariable("id") Integer id){
        departamentService.fshiDepartament(id);
    }

    @GetMapping("/lexo/punonjes")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartamentDto> lexoDepartamentPunonjes(){
        return departamentService.lexoDepartamentPunonjes();
    }


}
