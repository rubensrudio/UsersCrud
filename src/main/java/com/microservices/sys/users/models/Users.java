package com.microservices.sys.users.models;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users extends AbstractEntity {
    
    private static final long serialVersionUID = 1L;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String email;
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String password;
}