package com.bootcamp.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tp_programme")
public class Programme implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message="l'attribut nom doit �tre renseign�")
    @Column(length = 35)
    private String nom;
    
    @NotNull(message="l'attribut objectif doit �tre renseign�")
    @Column(length = 120)
    private String objectif;
    
    @NotNull(message="l'attribut dateDebut doit �tre renseign�")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeDebut;
    
    @NotNull(message="l'attribut dateFin doit �tre renseign�")
    @Column(length = 20)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDeFin;
    
    @NotNull(message="l'attribut fond doit �tre renseign�")
    @Column(scale =2 )
    private double budgetPrevisionnel;
    
    @NotNull(message="l'attribut budgetEffectif doit �tre renseign�")
    @Column(scale = 2)
    private double budgetEffectif;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programme")
    private List<Projet> projets;
    
    @OneToOne(fetch = FetchType.LAZY)
    private IndicateurPerformance indicateurPerformance;
    
    @OneToMany(mappedBy = "programme")
    private List<BailleurProgramme> bailleurs = new ArrayList<BailleurProgramme>();
    
    @OneToMany(mappedBy = "programme")
    private List<FournisseurProgramme> fournisseurs = new ArrayList<FournisseurProgramme>();
    
    @OneToMany(mappedBy = "programme")
    private List<BeneficiaireProgramme> beneficiaires = new ArrayList<BeneficiaireProgramme>();

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

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public Date getDateDeDebut() {
        return dateDeDebut;
    }

    public void setDateDeDebut(Date dateDeDebut) {
        this.dateDeDebut = dateDeDebut;
    }

    public Date getDateDeFin() {
        return dateDeFin;
    }

    public void setDateDeFin(Date dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public double getBudgetPrevisionnel() {
        return budgetPrevisionnel;
    }

    public void setBudgetPrevisionnel(double budgetPrevisionnel) {
        this.budgetPrevisionnel = budgetPrevisionnel;
    }

    public double getBudgetEffectif() {
        return budgetEffectif;
    }

    public void setBudgetEffectif(double budgetEffectif) {
        this.budgetEffectif = budgetEffectif;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public IndicateurPerformance getIndicateurPerformance() {
        return indicateurPerformance;
    }

    public void setIndicateurPerformance(IndicateurPerformance indicateurPerformance) {
        this.indicateurPerformance = indicateurPerformance;
    }

    public List<BailleurProgramme> getBailleurs() {
        return bailleurs;
    }

    public void setBailleurs(List<BailleurProgramme> bailleurs) {
        this.bailleurs = bailleurs;
    }

    public List<FournisseurProgramme> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<FournisseurProgramme> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public List<BeneficiaireProgramme> getBeneficiaires() {
        return beneficiaires;
    }

    public void setBeneficiaires(List<BeneficiaireProgramme> beneficiaires) {
        this.beneficiaires = beneficiaires;
    }

    @Override
    public String toString() {
        return "Programme{" + "id=" + id + ", nom=" + nom + ", objectif=" + objectif + ", dateDeDebut=" + dateDeDebut + ", dateDeFin=" + dateDeFin + ", budgetPrevisionnel=" + budgetPrevisionnel + ", budgetEffectif=" + budgetEffectif + ", projets=" + projets + ", indicateurPerformance=" + indicateurPerformance + ", bailleurs=" + bailleurs + ", fournisseurs=" + fournisseurs + ", beneficiaires=" + beneficiaires + '}';
    }
    
}
