package com.fzq.staffsystem.model;

public class Department {
    private int id;
    private String departmentId;
    private String name;
    private int stuffCount;
    public Department(){

    }
    public Department(String departmentId, String name){
        this.departmentId = departmentId;
        this.name = name;
        this.stuffCount = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStuffCount() {
        return stuffCount;
    }

    public void setStuffCount(int stuffCount) {
        this.stuffCount = stuffCount;
    }
}
