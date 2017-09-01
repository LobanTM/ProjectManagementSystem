package com.lobantm.hw02.model;

import java.util.List;

public class Project {
    private int id;
    private String nameProject;
    private Float cost;
    private Company company;
    private Customer customer;

    private List<String> developerList;

    public Project() {
    }

    public Project(int id, String nameProject, Float cost, Company company,
                   Customer customer, List<String> developerList ) {
        this.id = id;
        this.nameProject = nameProject;
        this.cost = cost;
        this.company = company;
        this.customer = customer;
        this.developerList = developerList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<String> getDeveloperList() {
        return developerList;
    }

    public void setDeveloperList(List<String> developerList) {
        this.developerList = developerList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", nameProject='" + nameProject + '\'' +
                ", cost=" + cost +
                ", company=" + company +
                ", customer=" + customer +
                ", developerList=" + developerList +
                '}';
    }
}
