package com.lidia.organization.controllers;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.services.DepartamentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DepartamentController {

    private final DepartamentService departamentService;

    public DepartamentController(DepartamentService departamentService) {
        this.departamentService = departamentService;
    }

    @PostMapping("/departament/krijo")
    public void krijoDepartament(
            @RequestBody DepartamentDto departamentDto){
        departamentService.krijoDepartament(departamentDto);
    }

    @GetMapping("/departament/kerko/{emer}")
    public DepartamentDto kerkoDepartament(
            @PathVariable String emer){
        return departamentService.kerkoDepartament(emer);
    }

    @GetMapping("/departament/lexo")
    public List<DepartamentDto> lexoDepartamentet(){
        return departamentService.lexoDepartamentet();
    }

    @DeleteMapping("/departament/fshi/{id}")
    public void fshiDepartament(
            @PathVariable("id") Integer id){
        departamentService.fshiDepartament(id);
    }

    @PostMapping("/departament/ndrysho")
    public void ndryshoEmer(
            @RequestBody DepartamentDto departamentDto){
        departamentService.ndryshoEmer(departamentDto);
    }

}
