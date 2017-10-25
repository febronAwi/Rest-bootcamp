/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.json.jsonClasses;

import com.bootcamp.jpa.entities.Bailleur;
import com.bootcamp.jpa.entities.Beneficiaire;
import com.bootcamp.jpa.entities.Fournisseur;
import com.bootcamp.jpa.entities.IndicateurPerformance;
import com.bootcamp.jpa.entities.Programme;
import com.bootcamp.jpa.entities.Projet;
import com.bootcamp.jpa.enums.TypeBailleur;
import com.bootcamp.jpa.repositories.BailleurRepository;
import com.bootcamp.jpa.repositories.BeneficiaireRepository;
import com.bootcamp.jpa.repositories.FournisseurRepository;
import com.bootcamp.jpa.repositories.IndicateurPerformanceRepository;
import com.bootcamp.jpa.repositories.ProgrammeRepository;
import com.bootcamp.jpa.repositories.ProjetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author edwigeg�d�on
 */
public class ClassesToJson<T> {
        
    //INSTANCIATION DE ObjectMapper
    ObjectMapper om = new ObjectMapper(); 
    
    // Methode qui prend le nom du fichier selon la classe et retourne l'objet de type File
    public static File generateFile(String fileName){
        File file = new File("JsonFolder/".concat(fileName));
        return file;
    }
    
    private final Class entityClass;
    public String className;
    
    public ClassesToJson(Class c) {
        this.entityClass = c;
        this.className = entityClass.getSimpleName();
    }
   
   
     public void convertObjectToJson(T ec) throws IOException, SQLException{
        
        // CREATION DE L'OBJET DANS LE FICHIER projet.json
            // SERIALIZATION
         File file = generateFile(className.toLowerCase()+".json");
        om.writeValue(file, ec);
        System.out.println("FICHIER  BIEN  CREER");
        
   }
        
}
