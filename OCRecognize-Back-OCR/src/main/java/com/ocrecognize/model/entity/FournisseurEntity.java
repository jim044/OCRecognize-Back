package com.ocrecognize.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FOURNISSEUR")
@Data
public class FournisseurEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "fournisseur_name", length = 65)
    private String fournisseurName;
}
