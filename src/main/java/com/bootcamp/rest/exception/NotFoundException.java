/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.exception;

import javax.ws.rs.core.Response;

public class NotFoundException extends Exception{
     	
	public static Response notFoundException(String msg,Exception e) {
            return Response.status(404).entity(msg+"!\n"+e).build();
	}
	
}
