package com.lidia.organization.model;

import jakarta.persistence.*;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titull;
    @Enumerated(EnumType.STRING)
    private StatusTask status = StatusTask.NEW;

    @ManyToOne
    @JoinColumn(name = "id_projekt")
    private Projekt projekt;

    @ManyToOne
    @JoinColumn(name = "id_punonjes")
    private Punonjes punonjes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitull() {
        return titull;
    }

    public void setTitull(String titull) {
        this.titull = titull;
    }

    public StatusTask getStatus() {
        return status;
    }

    public void setStatus(StatusTask status) {
        this.status = status;
    }

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public Punonjes getPunonjes() {
        return punonjes;
    }

    public void setPunonjes(Punonjes punonjes) {
        this.punonjes = punonjes;
    }
}
