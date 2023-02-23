package com.lidia.organization.services;

import com.lidia.organization.dto.DepartamentDto;
import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.model.Departament;
import com.lidia.organization.model.Punonjes;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.function.Function;

@Service
public class DepartamentService {

    private final DepartamentRepository departamentRepository;

    private final PunonjesRepository punonjesRepository;

    public DepartamentService(DepartamentRepository departamentRepository, PunonjesRepository punonjesRepository) {
        this.departamentRepository = departamentRepository;
        this.punonjesRepository = punonjesRepository;
    }

    public void krijoDepartament(DepartamentDto departamentDto) {
        Departament departament = new Departament();
        departament.setEmer(departamentDto.getEmer());
        departamentRepository.save(departament);
    }

    public DepartamentDto kerkoDepartament(String emer){
        return toDepartamentDto().apply(departamentRepository.findByEmer(emer));
    }

    public List<DepartamentDto> lexoDepartamentet(){
        return departamentRepository.findAll().stream().map(toDepartamentDto()).toList();
    }

    private static Function<Departament, DepartamentDto> toDepartamentDto() {
        return departament -> {
            DepartamentDto departamentDto = new DepartamentDto();
            departamentDto.setId(departament.getId());
            departamentDto.setEmer(departament.getEmer());
            List<PunonjesDto> punonjesDtos = departament.getPunonjesList().stream().map(toPunonjesDto()).toList();
            departamentDto.setPunonjesDtos(punonjesDtos);
            return departamentDto;
        };
    }

    private static Function<Punonjes, PunonjesDto> toPunonjesDto() {
        return punonjes -> {
            PunonjesDto punonjesDto = new PunonjesDto();
            punonjesDto.setId(punonjes.getId());
            punonjesDto.setEmer(punonjes.getEmer());
            punonjesDto.setEmail(punonjes.getEmail());
            return punonjesDto;
        };
    }

    // TODO - Use dto or not?
    public void fshiDepartament(int id){
        Departament departament = departamentRepository.findById(id);

        for (Punonjes punonjes : departament.getPunonjesList()) {
            punonjes.setDepartament(null);
            punonjesRepository.save(punonjes);
        }

        departamentRepository.deleteDepartamentById(id);
    }

    public void ndryshoEmer(int id, String emer){
        DepartamentDto departamentDto = toDepartamentDto().apply(departamentRepository.findById(id));
        departamentDto.setEmer(emer);
        Departament departament = toDepartament().apply(departamentDto);

        departamentRepository.save(departament);
    }

    private static Function<DepartamentDto, Departament> toDepartament() {
        return departamentDto -> {
            Departament departament = new Departament();
            departament.setId(departamentDto.getId());
            departament.setEmer(departamentDto.getEmer());
            return departament;
        };
    }

}
