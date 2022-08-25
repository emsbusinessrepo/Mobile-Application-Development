package com.example.week01t3;

public class Unit implements UnitInterface{

    public String Name;
    public String ID;

    public Unit(String name, String ID) {
        Name = name;
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public void showName() {
        System.out.println(getName());
    }

    @Override
    public void showCode() {
        System.out.println(getID());

    }
}
