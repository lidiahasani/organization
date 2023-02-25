package com.lidia.organization.services;

import com.lidia.organization.dto.PunonjesDto;
import com.lidia.organization.model.Punonjes;
import com.lidia.organization.model.Task;
import com.lidia.organization.repositories.DepartamentRepository;
import com.lidia.organization.repositories.PunonjesRepository;
import com.lidia.organization.repositories.TaskRepository;
import com.lidia.organization.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunonjesService {

    private final PunonjesRepository punonjesRepository;
    private final DepartamentRepository departamentRepository;
    private final TaskRepository taskRepository;

    private final Mapper mapper;

    public PunonjesService(PunonjesRepository punonjesRepository, DepartamentRepository departamentRepository, TaskRepository taskRepository, Mapper mapper) {
        this.punonjesRepository = punonjesRepository;
        this.departamentRepository = departamentRepository;
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public void shtoPunonjes(PunonjesDto punonjesDto) {
        punonjesRepository.save(mapper.toPunonjes().apply(punonjesDto));
    }

    public PunonjesDto kerkoPunonjes(String emer){
        if(punonjesRepository.findByEmer(emer) != null)
            return mapper.toPunonjesDto().apply(punonjesRepository.findByEmer(emer));
        else
            return null;
    }

    public List<PunonjesDto> lexoPunonjes(){
        return punonjesRepository.findAll().stream().map(mapper.toPunonjesDto()).toList();
    }

    public void fshiPunonjes(int id){
        Punonjes punonjes = punonjesRepository.findById(id);

        for (Task task : punonjes.getTaskList()) {
            task.setPunonjes(null);
            taskRepository.save(task);
        }

        punonjesRepository.deletePunonjesById(id);
    }

    public void ndryshoPunonjes(PunonjesDto punonjesDto){
        punonjesRepository.save(mapper.toPunonjes().apply(punonjesDto));
    }

    // TODO : DEFINE MAPPINGS OUTSIDE OF THE SERVICE CLASS

}
