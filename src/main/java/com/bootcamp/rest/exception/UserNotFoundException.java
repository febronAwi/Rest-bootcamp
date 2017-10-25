/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.exception;

import javax.ws.rs.core.Response;

public class UserNotFoundException {
     	
	public static Response userNotFoundException() {
            return Response.status(401).entity("Utilisateur non trouve !").build();
	}
	
}
