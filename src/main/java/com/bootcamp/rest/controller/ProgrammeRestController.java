/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.controller;

import com.bootcamp.jpa.entities.Personne;
import com.bootcamp.jpa.entities.Programme;
import com.bootcamp.jpa.repositories.PersonneRepository;
import com.bootcamp.jpa.repositories.ProgrammeRepository;
import com.bootcamp.rest.exception.SuccessMessage;
import com.bootcamp.rest.exception.TokenNotGenerateException;
import com.bootcamp.rest.exception.UserNotFoundException;
import com.bootcamp.rest.security.JavaJsonWebToken;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
//import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
//import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

@Path("/programme")
public class ProgrammeRestController {
    PersonneRepository psnr = new  PersonneRepository("tpJpa-mysql");
    ProgrammeRepository pr = new ProgrammeRepository("tpJpa-mysql");
    JavaJsonWebToken jt = new JavaJsonWebToken();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)//ou @Produces("application/json")
    public Response getAllProrammes(@Context HttpHeaders headers) {
        //MultivaluedMap<String,String> requestHeaders = headers.getRequestHeaders();
        /*
        try {
            jt.parseJWT(token);
            List<Programme> liste = pr.findAll();
            return Response.status(200).entity("verification du token réusssie. Voici la liste "+liste).build();
        } catch (Exception e) {
            return Response.status(404).entity("Pas d'enregistrement !").build();
            //e.printStackTrace();
         }*/
        Response resp =SuccessMessage.message("le header"+headers);
        return resp;
    }
   
    @POST
    @Path("/login")
    @Consumes("application/json")
    public void findProgrammeById(Personne obj) throws SQLException {
        String subject = obj.toString();
       String iat = "1422779638",id = "1";
       long tm = 300000;
       
        try {
            Personne psn = psnr.findByNomAndPwd(obj.getNom(), obj.getPwd());
            try {
            String token = jt.createJWT(iat, subject, tm);
                SuccessMessage.message("Retenez bien ce token: "+token);
        } catch (Exception e) {
                TokenNotGenerateException.generateTokenException();
         }
        } catch (Exception e) {
            UserNotFoundException.userNotFoundException();
        }
       
    }
    
    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Programme programme) throws SQLException {
        try {
            pr.create(programme);
            String output = "Bien creer";
        return Response.status(200).entity(output).build();
        } catch (Exception e) {
            return Response.status(404).entity("Erreur lors de la creation").build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Programme programme) throws SQLException {
        try {
            pr.update(programme);
            return Response.status(200).entity("Mise à jour bien faite").build();
        } catch (Exception e) {
            return Response.status(404).entity("Un probleme lors de la mise à jour").build();
        }
        
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") int valeur) throws SQLException {  
        Programme p;     
        try {
            p = pr.findById(valeur);
            pr.delete(p);
            return Response.status(200).entity("Le Programme d'id: "+valeur+" a bien été supprimé").build();
        } catch (Exception e) {
            return Response.status(404).entity("Un probleme lors de la suppression du programme d'id "+valeur).build();
        }
        
    }
    
     @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findProgrammeByFields(@PathParam("id") long id,@QueryParam("fields") String fields) throws SQLException, IntrospectionException, InvocationTargetException, IllegalArgumentException, IllegalAccessException {
            
        Field[] fieldsProgramme = Programme.class.getDeclaredFields();
        
        String[] rempli = fields.split(",");
        Programme prog = pr.findById(id);
        
         Map<String, Object> responseMap = new HashMap<String, Object>();
         
         PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Programme.class).getPropertyDescriptors();
       
         for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {

           Method method = propertyDescriptor.getReadMethod();

           if (check(rempli, propertyDescriptor.getName())) {
               responseMap.put(propertyDescriptor.getName(), method.invoke(prog));
           }
           //Logger log = new Logger();
                   
       }

       return Response.status(200).entity(responseMap).header("Access-Control-Allow-Origin", "*").build();// header donne la liste des domaines outorisé à utiliser le service
        
    }
     private boolean check(String[] fields, String field) {



       for (String field1 : fields) {

           if (field.equals(field1)) {

               return true;

           }

       }

       return false;

   }         
    }
