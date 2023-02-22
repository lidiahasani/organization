package com.lidia.organization.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Departament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departament")
    private List<Punonjes> punonjesList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departament")
    private List<Projekt> projektList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
