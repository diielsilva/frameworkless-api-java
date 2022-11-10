package com.diel.common.dtos.employee;

import java.io.Serializable;

public class SaveEmployee implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
