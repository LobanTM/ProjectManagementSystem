package com.lobantm.hw02.model;

public class Company {
    private  int id;
    private String nameCompany;

    public Company() {
    }

    public Company(int id, String nameCompany) {
        this.id = id;
        this.nameCompany = nameCompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    @Override
    public String toString() {
        return nameCompany;
    }
}
