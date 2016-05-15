package com.junapp.pms.entity;

import javax.persistence.*;
import javax.persistence.Entity;

import com.junapp.pms.security.PasswordHashUtils;

@Entity
@Table(name="user")
public class User implements PersistentEntity {
    @Id
    @GeneratedValue
    private Long id;
 
    @Column(unique=true)
    private String name;
    
    @Column(name="password_hash")
    private String passwordHash;
 
    protected User() {} //for hibernate
    
    public User(String name, String password) {
        this.name = name;
        this.passwordHash = PasswordHashUtils.hash(password, name);
    }
     
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPasswordHash() {
    	return this.passwordHash;
    }
}