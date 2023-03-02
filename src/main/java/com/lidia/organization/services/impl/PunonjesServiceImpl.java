package com.lidia.organization.services.impl;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.exception.EntityNotExistsException;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.security.dto.MessageResponse;
import com.lidia.organization.services.MapperService;
import com.lidia.organization.services.api.PunonjesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunonjesServiceImpl implements PunonjesService {

    private final PunonjesRepository punonjesRepository;
    private final TaskRepository taskRepository;
    private final MapperService mapper;

    public PunonjesServiceImpl(PunonjesRepository punonjesRepository, TaskRepository taskRepository, MapperService mapper) {
        this.punonjesRepository = punonjesRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<MessageResponse> regjistroPunonjes(PunonjesDto punonjesDto) {
        punonjesRepository.save(mapper.toPunonjes().apply(punonjesDto));
        return ResponseEntity.ok(new MessageResponse("Punonjesi u regjistrua me sukses!"));
    }

    @Override
    public PunonjesDto kerkoPunonjes(String emer){
        return punonjesRepository.findByEmer(emer).map(mapper.toPunonjesDto())
                .orElseThrow(() -> new EntityNotExistsException("Punonjesi nuk ekziston."));
    }

    @Override
    public List<PunonjesDto> lexoPunonjes(){
        return punonjesRepository.findAll().stream().map(mapper.toPunonjesDto()).toList();
    }

    @Override
    public void fshiPunonjes(int id){
        taskRepository.findByPunonjesId(id).forEach(task -> task.setPunonjes(null));
        punonjesRepository.deletePunonjesById(id);
    }

    @Override
    public void ndryshoPunonjes(PunonjesDto punonjesDto){
        punonjesRepository.save(mapper.toPunonjes().apply(punonjesDto));
    }

}
