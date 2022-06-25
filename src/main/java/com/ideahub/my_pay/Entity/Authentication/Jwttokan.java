package com.ideahub.my_pay.Entity.Authentication;
import java.io.Serializable;
public class Jwttokan implements Serializable{
    private static final long serialVersionUID = 1L;
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public Jwttokan setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Jwttokan setPassword(String password) {
        this.password = password;
        return this;
    }

    public Jwttokan(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
