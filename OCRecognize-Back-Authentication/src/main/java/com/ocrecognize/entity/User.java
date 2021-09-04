package com.ocrecognize.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

}
