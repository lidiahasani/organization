package com.lidia.organization.controllers;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.repositories.DepartamentRepositoryJdbc;
import com.lidia.organization.services.DepartmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartamentController {

    private final DepartamentRepositoryJdbc departamentRepository;

    private final DepartmentService departmentService;

    public DepartamentController(DepartamentRepositoryJdbc departamentRepository, DepartmentService departmentService) {
        this.departamentRepository = departamentRepository;
        this.departmentService = departmentService;
    }

    @PostMapping("/departament/krijo")
    public void krijoDepartament(
            @RequestBody DepartamentDto departamentDto){
        departmentService.krijoDepartament(departamentDto);
    }

    @GetMapping("/departament/kerko/{id}")
    public Optional<DepartamentDto> kerkoDepartament(
            @PathVariable("id") Integer id){
        return departamentRepository.kerkoDepartament(id);
    }

    @GetMapping("/departament/lexo")
    public List<DepartamentDto> lexoDepartamentet(){
        return departamentRepository.lexoDepartamentet();
    }

    @DeleteMapping("/departament/fshi/{id}")
    public void fshiDepartament(
            @PathVariable("id") Integer id){
        departamentRepository.fshiDepartament(id);
    }

    @PostMapping("/departament/ndrysho")
    public void ndryshoEmer(
            @RequestBody DepartamentDto departamentDto){
        departamentRepository.ndryshoEmer(departamentDto);
    }

}
