package com.lidia.organization.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Projekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titull;

    @Column(name = "data_nisje")
    private Date dataNisje;

    private Date dataPerfundim;

    @Enumerated(EnumType.STRING)
    private StatusProjekt status;

    @ManyToOne
    private Departament departament;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projekt")
    private List<Task> taskList = new ArrayList<>();

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

    public Date getDataNisje() {
        return dataNisje;
    }

    public void setDataNisje(Date dataNisje) {
        this.dataNisje = dataNisje;
    }

    public Date getDataPerfundim() {
        return dataPerfundim;
    }

    public void setDataPerfundim(Date dataPerfundim) {
        this.dataPerfundim = dataPerfundim;
    }

    public StatusProjekt getStatus() {
        return status;
    }

    public void setStatus(StatusProjekt status) {
        this.status = status;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
