package com.ocrecognize.model.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "DIRIGEANT")
@Data
public class DirigeantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenoms")
    private String prenoms;

    @Column(name = "annee_de_naissance")
    private String annee_de_naissance;

    @Column(name = "qualite")
    private String qualite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_siren", referencedColumnName = "siren")
    private CompanyEntity company;
}
