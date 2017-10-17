/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.jpa.repositories;

import com.bootcamp.jpa.entities.Beneficiaire;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author edwigeg�d�on
 */
public class BeneficiaireRepository extends BaseRepository<Beneficiaire>{
  
    public BeneficiaireRepository(String unitPersistence) {
        super(unitPersistence, Beneficiaire.class);
    }
 public  List<Beneficiaire> getBeneficiaireOfProgramme(long id){
           String str ="SELECT DISTINCT b FROM Beneficiaire b\n" +
                         "JOIN b.programmes bp JOIN bp.programme p WHERE p.id =:identifiant";
           Query query = getEntityManager().createQuery(str);
           query.setParameter("identifiant", id);
           return query.getResultList();
       }  
public  List<Beneficiaire> getBeneficiaireOfProjet(long id){
           String str ="SELECT DISTINCT b FROM Beneficiaire b\n" +
                         "JOIN b.projets p WHERE p.id =:identifiant";
           Query query = getEntityManager().createQuery(str);
           query.setParameter("identifiant", id);
           return query.getResultList();
       }  
}
