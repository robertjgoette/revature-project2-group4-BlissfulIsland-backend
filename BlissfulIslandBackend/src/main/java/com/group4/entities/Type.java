package com.group4.entities;

public class Type {
    private int typeID;
    private String name;
    private float cost;
    private int beds;
    private float baths;

    public Type(int typeID, String name, float cost, int beds, float baths) {
        this.typeID = typeID;
        this.name = name;
        this.cost = cost;
        this.beds = beds;
        this.baths = baths;
    }

    public Type() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "typeID=" + typeID +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", beds=" + beds +
                ", baths=" + baths +
                '}';
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public float getBaths() {
        return baths;
    }

    public void setBaths(float baths) {
        this.baths = baths;
    }
}
