package com.junapp.pms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="computer")
public class Computer implements PersistentEntity {
 
    @Id
    @GeneratedValue
    private Long id;
 
    @Column(name = "name")
    private String name;

    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="owner")
    private User owner;
      
    public User getOwner() {
		return owner;
	}
    
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Computer() {
        super();
    }
    public Computer(String name) {
        this.name = name;
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
}