/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.exception;

import javax.ws.rs.core.Response;


public class TokenVerifyException {
     	
	public static Response tokenException( ) {
            return Response.status(400).entity("Token non valide !").build();
	}
}