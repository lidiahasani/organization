package com.lidia.organization.services.impl;

import com.lidia.organization.dto.ProjektDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.repositories.ProjektRepository;
import com.lidia.organization.services.MapperService;
import com.lidia.organization.services.api.ProjektService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjektServiceImpl implements ProjektService {

    private final ProjektRepository projektRepository;
    private final MapperService mapper;

    public ProjektServiceImpl(ProjektRepository projektRepository, MapperService mapper) {
        this.projektRepository = projektRepository;
        this.mapper = mapper;
    }

    @Override
    public void shtoProjekt(ProjektDto projektDto){
        projektRepository.save(mapper.toProjekt().apply(projektDto));
    }

    @Override
    public ProjektDto kerkoProjekt(int id){
        return projektRepository.findById(id).map(mapper.toProjektDto())
                .orElseThrow(() -> new EntityNotExistsException("Projekti nuk ekziston."));
    }

    @Override
    public List<ProjektDto> lexoProjektet(){
        return projektRepository.findAll().stream().map(mapper.toProjektDto()).toList();
    }

    @Override
    public void fshiProjekt(int id){
        projektRepository.deleteProjektById(id);
    }

    @Override
    public void ndryshoProjekt(ProjektDto projektDto){
        projektRepository.save(mapper.toProjekt().apply(projektDto));
    }

    @Override
    public List<ProjektDto> lexoProjektetmeKushtTitull(String titull){
        return projektRepository.findAllByTitullEndingWith(titull).stream().map(mapper.toProjektDto()).toList();
    }

    @Override
    public List<ProjektDto> lexoProjektetmeKushtDate(Date date){
        return projektRepository.findAllByDataNisjeGreaterThan(date).stream().map(mapper.toProjektDto()).toList();
    }

}
