package com.lidia.organization.services;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunonjesService {

    private final PunonjesRepository punonjesRepository;
    private final TaskRepository taskRepository;
    private final Mapper mapper;

    public PunonjesService(PunonjesRepository punonjesRepository, TaskRepository taskRepository, Mapper mapper) {
        this.punonjesRepository = punonjesRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public void shtoPunonjes(PunonjesDto punonjesDto) {
        punonjesRepository.save(mapper.toPunonjes().apply(punonjesDto));
    }

    public PunonjesDto kerkoPunonjes(String emer){
        return punonjesRepository.findByEmer(emer).map(mapper.toPunonjesDto())
                .orElseThrow(() -> new EntityNotExistsException("Punonjesi nuk ekziston."));
    }

    public List<PunonjesDto> lexoPunonjes(){
        return punonjesRepository.findAll().stream().map(mapper.toPunonjesDto()).toList();
    }

    public void fshiPunonjes(int id){
        taskRepository.findByPunonjesId(id).forEach(task -> task.setPunonjes(null));
        punonjesRepository.deletePunonjesById(id);
    }

    public void ndryshoPunonjes(PunonjesDto punonjesDto){
        punonjesRepository.save(mapper.toPunonjes().apply(punonjesDto));
    }

}
