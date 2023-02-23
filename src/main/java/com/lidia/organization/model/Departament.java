package com.lidia.organization.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emer;

    @OneToMany(mappedBy = "departament")
    private List<Punonjes> punonjesList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departament")
    private List<Projekt> projektList = new ArrayList<>();

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

    public List<Punonjes> getPunonjesList() {
        return punonjesList;
    }

    public void setPunonjesList(List<Punonjes> punonjesList) {
        this.punonjesList = punonjesList;
    }

    public List<Projekt> getProjektList() {
        return projektList;
    }

    public void setProjektList(List<Projekt> projektList) {
        this.projektList = projektList;
    }
}
