package com.fzq.staffsystem.model;

public class Staff {
    private int id;
    private String staffId;
    private String name;
    private String headUrl;
    private String gender;
    private String birthYear;
    private String address;
    private String phoneNumber;
    private String departmentName;

    public Staff() {
    }

    public Staff(String staffId, String name, String headUrl, String gender, String birthYear, String address, String phoneNumber, String departmentName) {
        this.staffId = staffId;
        this.name = name;
        this.headUrl = headUrl;
        this.gender = gender;
        this.birthYear = birthYear;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
