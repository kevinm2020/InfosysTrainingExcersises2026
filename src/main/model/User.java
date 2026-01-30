package com.infosys.irs.model;

import org.hibernate.annotations.EmbeddableInstantiator;
import org.springframework.boot.convert.DataSizeUnit;

import javaz.validation.constraints.Email;
import javaz.validation.constraints.NotNull;
import javaz.validation.constraints.Size;

public class User {

    //Fields with validation annotations

    @NotNull(message = "Username cannot be blank")
    @Size(min = 4, max = 15, message = "Username must be between 4 and 15 characters")
    private String userId;

    @NotNull(message = "Password cannot be blank")
    @Size(min = 8, max = 15, message = "Password must be between 8 and 15 characters")
    private String password;

    @NotNull(message = "Name cannot be blank")
    @Size(min = 4, max = 15, message = "Name must be between 4 and 15 characters")
    private String name;

    @NotNull(message = "City cannot be blank")
    private String city;

    @NotNull(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Phone number cannot be blank")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    // Getters and Setters
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;   
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;   
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;   
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email; 
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone; 
    }

}
