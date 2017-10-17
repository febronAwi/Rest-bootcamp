package com.bootcamp.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by darextossa on 9/22/17.
 */

@Entity
@Table(name = "tp_beneficiaire")
@DiscriminatorValue("beneficiaire")
public class Beneficiaire extends Personne{
    
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "beneficiaire")
    private List<BeneficiaireProgramme> programmes = new ArrayList<BeneficiaireProgramme>();
    
    @ManyToMany(mappedBy = "beneficiaires")
    private List<Projet> projets = new ArrayList<Projet>();

    public List<BeneficiaireProgramme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<BeneficiaireProgramme> programmes) {
        this.programmes = programmes;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
    
    
}
