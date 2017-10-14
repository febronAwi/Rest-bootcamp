/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.jpa.jsonClasses;

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

/**
 *
 * @author edwigegédéon
 */
public class ClassesToJson {
        
    //INSTANCIATION DE ObjectMapper
    ObjectMapper om = new ObjectMapper(); 
    
    // Methode qui prend le nom du fichier selon la classe et retourne l'objet de type File
    public File generateFile(String fileName){
        File file = new File("JsonFolder/".concat(fileName));
        return file;
    }
    
    
    // Bailleur Json
    public void jsonBailleur() throws SQLException, IOException{
        
        //INSTANCIATION de Bailleurs
        Bailleur bailleur = new Bailleur();
        Bailleur b = new Bailleur();
        
        // CREATION D'UN BAILLEURS
        bailleur.setNom("jsonBailleur0");
        bailleur.setTypeBailleur(TypeBailleur.GOUVERNEMENT);   
        BailleurRepository br = new BailleurRepository("tpJpa-mysql");
        
        b.setNom("jsonbailleur1");
        
        
        //br.create(bailleur);
        //br.create(b);
        
        List<Bailleur> bl = new ArrayList<Bailleur>();
              
         bl.add(bailleur);
         bl.add(b);
        // CREATION DE PLUSIEURS OBJETS DANS LE FICHIER bailleur.json
            // SERIALIZATION 
         File file = generateFile("bailleur.json");
        om.writeValue(file, bl);
        System.out.println("FICHIER  BIEN  CREER");
        
        
        // DESEREALIZATION D'UN ARRAYJSON
        
        JsonArrayToJava jaj = new JsonArrayToJava<Bailleur>(generateFile("bailleur.json"));
        List<Bailleur> list = jaj.converteJsonArrayToObject(b);
        
        System.out.println(list.get(0).getNom());
        System.out.println(list.get(1).getNom());
          
    }
  
    // Beneficiaire et Json
   public void jsonBeneficiaire() throws SQLException, IOException{
               //INSTANCIATION Beneficiaire
        Beneficiaire b = new Beneficiaire();
        
        // CREATION D'UN BAILLEUR
        b.setNom("jsonBeneficiairer0");
        BeneficiaireRepository br = new BeneficiaireRepository("tpJpa-mysql");
        br.create(b);
        
        // CREATION DE L'OBJET DANS LE FICHIER bailleur.json
            // SERIALIZATION
        File file = generateFile("beneficiaire.json");
        om.writeValue(file, b);
        System.out.println("FICHIER  BIEN  CREER");
        
        // DESERIALIZATION
        Beneficiaire newBeneficiaire = om.readValue(file, Beneficiaire.class );
        System.out.println("objet bien forme");
        String nom = newBeneficiaire.getNom();
         System.out.println("Nom: "+nom);
   }
   
   // fournissuer and json
   public void jsonFournisseur() throws SQLException, IOException{
       //INSTANCIATION de Fournisseur
        Fournisseur f = new Fournisseur();
        
        // CREATION D'UN BAILLEUR
        f.setNom("jsonFournisseur0");
        FournisseurRepository fr = new FournisseurRepository("tpJpa-mysql");
        fr.create(f);
        
        // CREATION DE L'OBJET DANS LE FICHIER bailleur.json
            // SERIALIZATION
         File file = generateFile("fournisseur.json");
        om.writeValue(file, f);
        System.out.println("FICHIER  BIEN  CREER");
        
        // DESERIALIZATION
        Fournisseur newBailleur = om.readValue(file, Fournisseur.class );
        System.out.println("objet bien forme");
        String nom = newBailleur.getNom();
        System.out.println("Nom: "+nom);
        
   }
   
   // indicateur and json
   public void jsonIndicateur() throws SQLException, IOException{
        //INSTANCIATION de Bailleur
        IndicateurPerformance indicateurPerformance = new IndicateurPerformance();
        
        // CREATION D'UN BAILLEUR
        indicateurPerformance.setLibelle("jsonIndicateurPerformance0");  
        IndicateurPerformanceRepository ir = new IndicateurPerformanceRepository("tpJpa-mysql");
        ir.create(indicateurPerformance);
        
        // CREATION DE L'OBJET DANS LE FICHIER indicateurPerformance.json
            // SERIALIZATION
         File file = generateFile("indicateurPerformance.json");
        om.writeValue(file, indicateurPerformance);
        System.out.println("FICHIER  BIEN  CREER");
        
        // DESERIALIZATION
        IndicateurPerformance newIndicateurPerformance = om.readValue(file, IndicateurPerformance.class );
        System.out.println("objet bien forme");
        String nom = newIndicateurPerformance.getLibelle();
         System.out.println("Nom: "+nom);
   }
   
   // programme and json
   public void jsonProgramme() throws SQLException, IOException{
       //INSTANCIATION de Bailleur
        Programme programme = new Programme();
        
        // CREATION D'UN BAILLEUR
        programme.setNom("jsonProgramme0");  
        ProgrammeRepository gramr = new ProgrammeRepository("tpJpa-mysql");
        gramr.create(programme);
        
        // CREATION DE L'OBJET DANS LE FICHIER programme.json
            // SERIALIZATION
         File file = generateFile("programme.json");
        om.writeValue(file, programme);
        System.out.println("FICHIER  BIEN  CREER");
        
        // DESERIALIZATION
        Programme newProgramme = om.readValue(file, Programme.class );
        System.out.println("objet bien forme");
        String nom = newProgramme.getNom();
         System.out.println("Nom: "+nom);
   }
   
   public void jsonProjet() throws IOException, SQLException{
       //INSTANCIATION de Bailleur
        Projet projet = new Projet();
        
        // CREATION D'UN BAILLEUR
        projet.setNom("jsonProjet0");
        projet.setObjectif("win or win");
        ProjetRepository pr = new ProjetRepository("tpJpa-mysql");
        pr.create(projet);
        
        // CREATION DE L'OBJET DANS LE FICHIER projet.json
            // SERIALIZATION
         File file = generateFile("projet.json");
        om.writeValue(file, projet);
        System.out.println("FICHIER  BIEN  CREER");
        
        // DESERIALIZATION
        Projet newProjet = om.readValue(file, Projet.class );
        System.out.println("objet bien forme");
        String nom = newProjet.getNom();
         System.out.println("Nom: "+nom);
   }
}
