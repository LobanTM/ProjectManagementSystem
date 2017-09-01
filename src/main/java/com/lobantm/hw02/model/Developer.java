package com.lobantm.hw02.model;

import java.util.List;

public class Developer {
    private  int id;
    private String nameDeveloper;
    private  int age;
    private String email;
    private Company company;
    private Float salary;
    private List<Skill> skillList;
    private List<String> projectList;

    public Developer() {
    }

    public Developer(int id, String nameDeveloper, int age, String email,
                     Company company, Float salary,
                     List<Skill> skillList, List<String> projectList) {
        this.id = id;
        this.nameDeveloper = nameDeveloper;
        this.age = age;
        this.email = email;
        this.company = company;
        this.salary = salary;
        this.skillList = skillList;
        this.projectList = projectList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDeveloper() {
        return nameDeveloper;
    }

    public void setNameDeveloper(String nameDeveloper) {
        this.nameDeveloper = nameDeveloper;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", nameDeveloper='" + nameDeveloper + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", company=" + company +
                ", salary=" + salary +
                ", skills=" + skillList +
                ", projects=" + projectList +
                '}';
    }
}
