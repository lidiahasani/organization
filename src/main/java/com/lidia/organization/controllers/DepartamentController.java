package com.lidia.organization.controllers;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.repositories.DepartamentRepositoryJdbc;
import com.lidia.organization.services.DepartamentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DepartamentController {

    private final DepartamentRepositoryJdbc departamentRepository;

    private final DepartamentService departamentService;

    public DepartamentController(DepartamentRepositoryJdbc departamentRepository, DepartamentService departmentService) {
        this.departamentRepository = departamentRepository;
        this.departamentService = departmentService;
    }

    @PostMapping("/departament/krijo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void krijoDepartament(
            @RequestBody DepartamentDto departamentDto){
        departamentService.krijoDepartament(departamentDto);
    }

    @GetMapping("/departament/kerko/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public DepartamentDto kerkoDepartament(
            @PathVariable("id") Integer id){
        return departamentRepository.kerkoDepartament(id);
    }

    @GetMapping("/departament/lexo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartamentDto> lexoDepartamentet(){
        return departamentRepository.lexoDepartamentet();
    }

    @DeleteMapping("/departament/fshi/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void fshiDepartament(
            @PathVariable("id") Integer id){
        departamentRepository.fshiDepartament(id);
    }

    @PostMapping("/departament/ndrysho")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public void ndryshoEmer(
            @RequestBody DepartamentDto departamentDto){
        departamentService.ndryshoEmer(departamentDto);
    }

    @GetMapping("/departament/lexo/punonjes")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DEPARTMENT_MANAGER')")
    public List<DepartamentDto> lexoDepartamentPunonjes(){
        return departamentRepository.lexoDepartamentPunonjes();
    }


}
