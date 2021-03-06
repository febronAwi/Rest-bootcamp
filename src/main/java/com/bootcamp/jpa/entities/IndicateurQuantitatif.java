package com.bootcamp.jpa.entities;

import com.bootcamp.jpa.repositories.IndicateurQuantitatifRepository;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tp_indicateur_quantitatif")
public class IndicateurQuantitatif implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message="l'attribut nom doit etre renseigne")
    @Column(length = 25)
    private String nom;
    
    @NotNull(message="l'attribut propriete doit etre renseigne")
     @Column(length = 20)
    private String propriete;
    
    @NotNull(message="l'attribut valeur doit etre renseigne")
     @Column(length = 20)
    private int valeur;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indicateurPerformance", referencedColumnName = "id")
    private IndicateurPerformance indicateurPerformance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPropriete() {
        return propriete;
    }

    public void setPropriete(String propriete) {
        this.propriete = propriete;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public IndicateurPerformance getIndicateurPerformance() {
        return indicateurPerformance;
    }

    public void setIndicateurPerformance(IndicateurPerformance indicateurPerformance) {
        this.indicateurPerformance = indicateurPerformance;
    }
    
    
}
