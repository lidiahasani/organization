package com.lidia.organization.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjektDto {

    private int id;

    private String titull;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataNisje;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate dataPerfundim;

    private String status;

    private int departamentId;

    private List<TaskDto> taskDtoList = new ArrayList<>();

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

    public LocalDate getDataNisje() {
        return dataNisje;
    }

    public void setDataNisje(LocalDate dataNisje) {
        this.dataNisje = dataNisje;
    }

    public LocalDate getDataPerfundim() {
        return dataPerfundim;
    }

    public void setDataPerfundim(LocalDate dataPerfundim) {
        this.dataPerfundim = dataPerfundim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(int departamentId) {
        this.departamentId = departamentId;
    }

    public List<TaskDto> getTaskDtoList() {
        return taskDtoList;
    }

    @JsonIgnoreProperties({"projektId"})
    public void setTaskDtoList(List<TaskDto> taskDtoList) {
        this.taskDtoList = taskDtoList;
    }
}
