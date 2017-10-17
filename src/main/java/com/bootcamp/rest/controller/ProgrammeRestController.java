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

@Path("/programme")
public class ProgrammeRestController {
    
            ProgrammeRepository pr = new ProgrammeRepository("tpJpa-mysql");

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)//ou @Produces("application/json")
    public Response getAllProrammes() {
        
        try {
            List<Programme> liste = pr.findAll();
            return Response.status(200).entity(liste).build();
        } catch (Exception e) {
            return Response.status(404).entity("Pas d'enregistrement !").build();
            //e.printStackTrace();
         }
        
    }
   
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response findProgrammeById(@PathParam("id") int valeur) throws SQLException {
            
             try {
            Programme prog = pr.findById(valeur);
            return Response.status(200).entity(prog).build();
        } catch (Exception e) {
            return Response.status(404).entity("Pas d'enregistrement à ce id!").build();
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
}
