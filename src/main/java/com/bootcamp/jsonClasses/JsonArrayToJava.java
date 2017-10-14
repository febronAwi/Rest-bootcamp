package com.bootcamp.jpa.jsonClasses;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author edwigegédéon
 * @param <T>
 */
public class JsonArrayToJava<T> {

    File file;
    public JsonArrayToJava(File file) {
        this.file = file;
    }
    
    
    public List<T> converteJsonArrayToObject(Object obj) throws IOException{
           Class c = obj.getClass();
        //INSTANCIATION DE ObjectMapper
    ObjectMapper om = new ObjectMapper(); 
    
         List<T> list = om.readValue(this.file,
        TypeFactory.defaultInstance().constructCollectionType(List.class,  
       obj.getClass()) );
         
         return list;
    }
   
}
