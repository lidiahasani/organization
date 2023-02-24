package com.lidia.organization.services;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.model.Punonjes;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class PunonjesService {

    private final PunonjesRepository punonjesRepository;

    private final DepartamentRepository departamentRepository;

    public PunonjesService(PunonjesRepository punonjesRepository, DepartamentRepository departamentRepository) {
        this.punonjesRepository = punonjesRepository;
        this.departamentRepository = departamentRepository;
    }

    public void shtoPunonjes(PunonjesDto punonjesDto) {
        punonjesRepository.save(toPunonjes().apply(punonjesDto));
    }

    public PunonjesDto kerkoPunonjes(String emer){
        if(punonjesRepository.findByEmer(emer) != null)
            return toPunonjesDto().apply(punonjesRepository.findByEmer(emer));
        else
            return null;
    }

    public List<PunonjesDto> lexoPunonjes(){
        return punonjesRepository.findAll().stream().map(toPunonjesDto()).toList();
    }

    // TODO : Use dto or not?
    public void fshiPunonjes(int id){
        // Punonjes punonjes = punonjesRepository.findById(id);

                //set task id null

        punonjesRepository.deletePunonjesById(id);
    }

    public void ndryshoPunonjes(PunonjesDto punonjesDto){
        punonjesRepository.save(toPunonjes().apply(punonjesDto));
    }

    private static Function<Punonjes, PunonjesDto> toPunonjesDto() {
        return punonjes -> {
            PunonjesDto punonjesDto = new PunonjesDto();
            punonjesDto.setId(punonjes.getId());
            punonjesDto.setEmer(punonjes.getEmer());
            punonjesDto.setEmail(punonjes.getEmail());
            if(punonjes.getDepartament() != null){
                punonjesDto.setDepartamentId(punonjes.getDepartament().getId());
            }
         // List<TaskDto> taskDtos = punonjes.getTaskList().stream().map(toTaskDto()).toList();
            return punonjesDto;
        };
    }

    // TODO : FIX UPDATES
    private Function<PunonjesDto, Punonjes> toPunonjes() {
        return punonjesDto -> {
            Punonjes punonjes = new Punonjes();
            punonjes.setId(punonjesDto.getId());
            punonjes.setEmer(punonjesDto.getEmer());
            punonjes.setEmail(punonjesDto.getEmail());
            if(punonjesDto.getDepartamentId() != 0){
                punonjes.setDepartament(departamentRepository.findById(punonjesDto.getDepartamentId()));
            }
            else
                punonjes.setDepartament(null);
            return punonjes;
        };
    }
}
