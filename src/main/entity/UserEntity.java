//Entities class are used to map a class to a database table
//it's easies to work with classes rather than dealing with 
// SQL queries directly
//Entities classes enable Object-Relational Mapping (ORM) frameworks like Hibernate
//to automatically handle database operations based on the class structure
//once an entity class is defined, developers can use it to perform CRUD operations

package com.infosys.irs.entity;

import java.lang.annotation.Inherited;

import javax.persistance.Column;
import javax.persistance.Entity;
import javax.persistance.ID;
import javax.persistance.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserEntity {

    @ID
    @Column(name = "userid")
    private String userId;
    private String password;
    private String name;
    private String city;
    private String email;
    private String phone;

    //Methods

    public String getUserID() {
        return userID;
    }
    public void setUserId(String userId)
    {
        this.userID = userId;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getCity()
    {
        return this.city;
    }
    public void setCity(String city)
    {
        this.city = city;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }



    
}
