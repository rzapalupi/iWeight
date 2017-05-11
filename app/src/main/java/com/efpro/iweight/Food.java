package com.efpro.iweight;

public class Food {

    private int id;
    private String name;
    private int calorie;
    public Food()
    {
    }
    public Food(int id,String name,int calorie)
    {
        this.id=id;
        this.name=name;
        this.calorie=calorie;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public int getId() {
        return id;
    }
    public int getCalorie() {
        return calorie;
    }
    public String getName() { return name; }
}
