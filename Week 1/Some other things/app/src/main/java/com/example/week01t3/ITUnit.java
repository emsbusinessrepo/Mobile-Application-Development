package com.example.week01t3;

public class ITUnit extends Unit {

    public String software;

    public ITUnit(String name, String ID, String software) {
        super(name, ID);
        this.software = software;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }
}
