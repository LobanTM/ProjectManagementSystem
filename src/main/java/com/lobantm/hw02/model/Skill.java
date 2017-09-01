package com.lobantm.hw02.model;

public class Skill {
    private int id;
    private  String nameSkill;

    public Skill() {
    }

    public Skill(int id, String nameSkill) {
        this.id = id;
        this.nameSkill = nameSkill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSkill() {
        return nameSkill;
    }

    public void setNameSkill(String nameSkill) {
        this.nameSkill = nameSkill;
    }

    @Override
    public String toString() {
        return nameSkill ;
    }
}
