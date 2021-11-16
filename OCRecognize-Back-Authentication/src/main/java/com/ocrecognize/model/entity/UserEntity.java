package com.ocrecognize.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ManyToOne
    @JoinColumn(name="role_id")
    @JsonBackReference
    private RoleEntity roleEntity;

}
