package com.lidia.organization.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

public class PunonjesDto {

    private int id;

    private String emer;

    private String email;

    private String password;

    private int departamentId;

    private List<TaskDto> taskDtoList = new ArrayList<>();

    private List<RoleDto> roleDtoList = new ArrayList<>();

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

    @Email
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

    public int getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(int departamentId) {
        this.departamentId = departamentId;
    }

    public List<TaskDto> getTaskDtoList() {
        return taskDtoList;
    }

    @JsonIgnoreProperties({"punonjesId"})
    public void setTaskDtoList(List<TaskDto> taskDtoList) {
        this.taskDtoList = taskDtoList;
    }

    public List<RoleDto> getRoleDtoList() {
        return roleDtoList;
    }

    public void setRoleDtoList(List<RoleDto> roleDtoList) {
        this.roleDtoList = roleDtoList;
    }

}
