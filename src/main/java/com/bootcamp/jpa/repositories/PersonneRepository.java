/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.jpa.repositories;

import com.bootcamp.jpa.entities.Personne;
import javax.persistence.Query;

public class PersonneRepository extends BaseRepository<Personne>{
  
    public PersonneRepository(String unitPersistence) {
        super(unitPersistence, Personne.class);
    }
    
    public Personne findByNomAndPwd(String nom, String pwd){
         String str = "select obj from Personne obj where obj.nom="+nom+" andwhere obj.pwd="+pwd;
            Query query = getEntityManager().createQuery(str);
            
            Personne result = (Personne) query.getSingleResult();
            
            return result;
    }
}
