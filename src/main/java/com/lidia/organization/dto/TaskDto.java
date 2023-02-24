package com.lidia.organization.dto;

public class TaskDto {

    private int id;

    private String titull;

    private String status;

    private int projektId;

    private int punonjesId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public int getPunonjesId() {
        return punonjesId;
    }

    public void setPunonjesId(int punonjesId) {
        this.punonjesId = punonjesId;
    }
}
