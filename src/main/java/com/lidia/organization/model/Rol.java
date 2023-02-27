package com.lidia.organization.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emer;

    @ManyToMany(mappedBy = "role")
    private List<Punonjes> punonjes = new ArrayList<>();

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

    public List<Punonjes> getPunonjes() {
        return punonjes;
    }

    public void setPunonjes(List<Punonjes> punonjes) {
        this.punonjes = punonjes;
    }
}
