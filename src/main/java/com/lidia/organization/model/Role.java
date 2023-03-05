package com.lidia.organization.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="rol")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ERole emer;

    @ManyToMany(mappedBy = "role")
    private List<Punonjes> punonjes = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ERole getEmer() {
        return emer;
    }

    public void setEmer(ERole emer) {
        this.emer = emer;
    }

    public List<Punonjes> getPunonjes() {
        return punonjes;
    }

    public void setPunonjes(List<Punonjes> punonjes) {
        this.punonjes = punonjes;
    }

}
