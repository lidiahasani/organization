package com.lidia.organization.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

public class DepartamentDto {

    private int id;

    private String emer;

    private List<PunonjesDto> punonjesDtos = new ArrayList<>();

 //   private List<ProjektDto> projektList = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmer() {
        return emer;
    }

    public void setEmer(String emer) {
        this.emer = emer;
    }

    public List<PunonjesDto> getPunonjesDtos() {
        return punonjesDtos;
    }

    @JsonIgnoreProperties({"departamentId"})
    public void setPunonjesDtos(List<PunonjesDto> punonjesDtos) {
        this.punonjesDtos = punonjesDtos;
    }
}


