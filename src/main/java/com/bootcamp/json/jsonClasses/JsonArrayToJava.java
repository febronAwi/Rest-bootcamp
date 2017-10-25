package com.bootcamp.json.jsonClasses;

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
 * @author edwigeg�d�on
 * @param <T>
 */
public class JsonArrayToJava<T> {
    
    public List<T> converteJsonArrayToObject(File file,Object obj) throws IOException{
        //INSTANCIATION DE ObjectMapper
    ObjectMapper om = new ObjectMapper(); 
    
         List<T> list = om.readValue(file,
        TypeFactory.defaultInstance().constructCollectionType(List.class,  
       obj.getClass()) );
         
         return list;
    }
    /*
    public T converteJsonToObject(File file,Object obj) throws IOException{
        //INSTANCIATION DE ObjectMapper
    ObjectMapper om = new ObjectMapper(); 
    
         T result = om.readValue(file, obj.getClass());
         
         return result;
    }
   */
}
