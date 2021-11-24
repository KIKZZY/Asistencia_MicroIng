package com.kmac.asistenciamicroing;

public class Cargo {

    String id;
    String nombrecargo;

    public Cargo(String id, String nombrecargo) {
        this.id = id;
        this.nombrecargo = nombrecargo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }


    @Override
    public String toString() {
        return nombrecargo;
    }
}


