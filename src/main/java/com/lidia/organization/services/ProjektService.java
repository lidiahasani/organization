package com.lidia.organization.services;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjektService {

    //TODO: To all services, add exceptions if entity is not found

    private final ProjektRepository projektRepository;

    private final Mapper mapper;

    public ProjektService(ProjektRepository projektRepository, Mapper mapper) {
        this.projektRepository = projektRepository;
        this.mapper = mapper;
    }

    public void shtoProjekt(ProjektDto projektDto){
        projektRepository.save(mapper.toProjekt().apply(projektDto));
    }

    public ProjektDto kerkoProjekt(int id){
        if(projektRepository.findById(id) != null)
            return mapper.toProjektDto().apply(projektRepository.findById(id));
        else
            throw new EntityNotExistsException("Projekti nuk ekziston");
    }

    public List<ProjektDto> lexoProjektet(){
        return projektRepository.findAll().stream().map(mapper.toProjektDto()).toList();
    }

    public void fshiProjekt(int id){
        projektRepository.deleteProjektById(id);
    }

    public void ndryshoProjekt(ProjektDto projektDto){
        projektRepository.save(mapper.toProjekt().apply(projektDto));
    }

}
