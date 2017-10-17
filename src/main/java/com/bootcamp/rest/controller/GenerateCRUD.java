/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.controller;

import com.bootcamp.jpa.entities.Programme;
import com.bootcamp.jpa.repositories.ProgrammeRepository;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/generate")
public class GenerateCRUD {
    /*Class c;
    String className;
    public GenerateCRUD(Class c) {
        this.c = c;
        this.className = c.getSimpleName();
    }*/
    
    
    @GET
    @Path("{name}/all")
    @Produces(MediaType.APPLICATION_JSON)//ou @Produces("application/json")
    public Response getAllProrammes(@PathParam("name") String className) {
        
        ProgrammeRepository pr = new ProgrammeRepository("tpJpa-mysql");
        
        try {
            List<Programme> liste = pr.findAll();
            return Response.status(200).entity(liste).build();
        } catch (Exception e) {
            return Response.status(404).entity("Pas d'enregistrement !").build();
            //e.printStackTrace();
         }
        
    }
}
