/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.exception;

import javax.ws.rs.core.Response;

public class AuthentificationException extends Exception{
     	
	public static Response auth(String msg,Object obj) {
            return Response.status(401).entity(msg+"!\n"+obj).build();
	}
	
}
