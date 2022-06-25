package com.ideahub.my_pay.Entity;

import java.io.Serializable;

public class Test implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public Test setName(String name) {
        this.name = name;
        return this;
    }

    public Test(String name) {
        this.name = name;
    }

}
