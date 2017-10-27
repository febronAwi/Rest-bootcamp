/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp;

import com.bootcamp.jpa.entities.Bailleur;
import com.bootcamp.jpa.entities.BailleurProgramme;
import com.bootcamp.jpa.entities.Beneficiaire;
import com.bootcamp.jpa.entities.BeneficiaireProgramme;
import com.bootcamp.jpa.entities.Programme;
import com.bootcamp.jpa.repositories.BailleurProgrammeRepository;
import com.bootcamp.jpa.repositories.BailleurRepository;
import com.bootcamp.jpa.repositories.BeneficiaireProgrammeRepository;
import com.bootcamp.jpa.repositories.BeneficiaireRepository;
import com.bootcamp.jpa.repositories.ProgrammeRepository;
import java.sql.SQLException;
import org.testng.annotations.Test;

public class RequetesTest {
    @Test
    public void requestsTest() throws SQLException{
        /* TEST DES REQUETES */
        
        // INSTANCIATION DES CLASSES CONCERNEES
            //BalleurProgramme et BeneficiaireProgramme
        BailleurProgramme bp = new BailleurProgramme();
        BeneficiaireProgramme benp = new BeneficiaireProgramme();
        
        // ISTANCIATIONS DES REPOSITORIES CONCERNEES
            //BalleurProgramme et BeneficiaireProgramme
        BailleurProgrammeRepository bpr = new BailleurProgrammeRepository("tpJpa-mysql");
        BeneficiaireProgrammeRepository benpr = new BeneficiaireProgrammeRepository("tpJpa-mysql");
            //Balleur, Programme et Beneficiaire
        BailleurRepository br = new BailleurRepository("tpJpa-mysql");
        BeneficiaireRepository benr = new BeneficiaireRepository("tpJpa-mysql");
        ProgrammeRepository pr = new ProgrammeRepository("tpJpa-mysql");
        
        // find de bailleur, beneficiaire, programme
        Bailleur b = br.findByProperty("id", 1).get(0);
        Beneficiaire ben = benr.findByProperty("nom", "grav").get(0);
        Programme p = pr.findByProperty("id", 1).get(0);
        
      // AFFECTATIONS
        // dans BailleurProgramme
      bp.setBailleur(b);
      bp.setProgramme(p);
      bp.setFond(52);
      // dans BeneficiaireProgramme
     benp.setBeneficiaire(ben);
     benp.setProgramme(p);
     benp.setTypeBenefice("entie");
     
   // CREATION
   //bpr.create(bp);
   benpr.create(benp);
   
   // TEST DES REQUETES
   br.getBailleurOfProgramme(1);
   benr.getBeneficiaireOfProgramme(1);
        
    }
  }
