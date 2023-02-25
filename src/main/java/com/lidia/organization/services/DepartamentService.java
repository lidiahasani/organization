//package com.lidia.organization.services;
//
//import com.lidia.organization.dto.DepartamentDto;
//import com.lidia.organization.dto.ProjektDto;
//import com.lidia.organization.dto.PunonjesDto;
//import com.lidia.organization.model.Departament;
//import com.lidia.organization.model.Projekt;
//import com.lidia.organization.model.Punonjes;
//import com.lidia.organization.repositories.DepartamentRepository;
//import com.lidia.organization.repositories.ProjektRepository;
//import com.lidia.organization.repositories.PunonjesRepository;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.function.Function;
//
//
//// TODO : IMPLEMENT USING JDBC
//@Service
//public class DepartamentService {
//
//    private final DepartamentRepository departamentRepository;
//
//    private final PunonjesRepository punonjesRepository;
//
//    private final ProjektRepository projektRepository;
//
//    public DepartamentService(DepartamentRepository departamentRepository, PunonjesRepository punonjesRepository, ProjektRepository projektRepository) {
//        this.departamentRepository = departamentRepository;
//        this.punonjesRepository = punonjesRepository;
//        this.projektRepository = projektRepository;
//    }
//
//    public void krijoDepartament(DepartamentDto departamentDto) {
//        departamentRepository.save(toDepartament().apply(departamentDto));
//    }
//
//    public DepartamentDto kerkoDepartament(String emer){
//        if(departamentRepository.findByEmer(emer) != null)
//            return toDepartamentDto().apply(departamentRepository.findByEmer(emer));
//        else
//            return null;
//    }
//
//    public List<DepartamentDto> lexoDepartamentet(){
//        return departamentRepository.findAll().stream().map(toDepartamentDto()).toList();
//    }
//
//    // TODO - Use dto or not?
//    public void fshiDepartament(int id){
//        Departament departament = departamentRepository.findById(id);
//
//        for (Punonjes punonjes : departament.getPunonjesList()) {
//            punonjes.setDepartament(null);
//            punonjesRepository.save(punonjes);
//        }
//
//        for (Projekt projekt : departament.getProjektList()) {
//            projekt.setDepartament(null);
//            projektRepository.save(projekt);
//        }
//
//        departamentRepository.deleteDepartamentById(id);
//    }
//
//    public void ndryshoEmer(DepartamentDto departamentDto){
//        departamentRepository.save(toDepartament().apply(departamentDto));
//    }
//
//    private static Function<DepartamentDto, Departament> toDepartament() {
//        return departamentDto -> {
//            Departament departament = new Departament();
//            departament.setId(departamentDto.getId());
//            departament.setEmer(departamentDto.getEmer());
//            return departament;
//        };
//    }
//
//    private static Function<Departament, DepartamentDto> toDepartamentDto() {
//        return departament -> {
//            DepartamentDto departamentDto = new DepartamentDto();
//            departamentDto.setId(departament.getId());
//            departamentDto.setEmer(departament.getEmer());
//            List<PunonjesDto> punonjesDtos = departament.getPunonjesList().stream().map(toPunonjesDto()).toList();
//            List<ProjektDto> projektDtos = departament.getProjektList().stream().map(toProjektDto()).toList();
//            departamentDto.setPunonjesDtos(punonjesDtos);
//            departamentDto.setProjektDtos(projektDtos);
//            return departamentDto;
//        };
//    }
//
//    private static Function<Punonjes, PunonjesDto> toPunonjesDto() {
//        return punonjes -> {
//            PunonjesDto punonjesDto = new PunonjesDto();
//            punonjesDto.setId(punonjes.getId());
//            punonjesDto.setEmer(punonjes.getEmer());
//            punonjesDto.setEmail(punonjes.getEmail());
//            return punonjesDto;
//        };
//    }
//
//    private static Function<Projekt, ProjektDto> toProjektDto() {
//        return projekt -> {
//            ProjektDto projektDto = new ProjektDto();
//            projektDto.setId(projekt.getId());
//            projektDto.setTitull(projekt.getTitull());
//            projektDto.setDataNisje(projekt.getDataNisje());
//            projektDto.setDataPerfundim(projekt.getDataPerfundim());
//            projektDto.setStatus(String.valueOf(projekt.getStatus()));
//            return projektDto;
//        };
//    }
//
//}
