package com.ideahub.my_pay.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customer_id;
    @NotNull
    private String name;
    private String address;
    @NotNull
    private String email;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Column(columnDefinition = "LONGTEXT")
    private String token;
    @Size(min = 8, message = "Password should have atleast 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getToken() {
        return token;
    }

    public Customer setToken(String token) {
        this.token = token;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }


    public Integer getCustomer_id() {
        return customer_id;
    }

    public Customer setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    public Customer(Integer customer_id, String name, String address, String email, String phone, String token, String password) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.token = token;
        this.password = password;
    }

    public Customer() {

    }
}
