package com.lidia.organization.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Punonjes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String emer;

    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_departament")
    private Departament departament;

    @OneToMany(mappedBy = "punonjes")
    private List<Task> taskList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "punonjes_rol",
        joinColumns = @JoinColumn(name = "id_punonjes"),
        inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private List<Rol> role = new ArrayList<>();

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Rol> getRole() {
        return role;
    }

    public void setRole(List<Rol> role) {
        this.role = role;
    }
}
