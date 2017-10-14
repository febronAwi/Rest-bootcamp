/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.jpa.repositories;

import com.bootcamp.jpa.entities.Projet;

public class ProjetRepository extends BaseRepository<Projet>{
  
    public ProjetRepository(String unitPersistence) {
        super(unitPersistence, Projet.class);
    }
}
