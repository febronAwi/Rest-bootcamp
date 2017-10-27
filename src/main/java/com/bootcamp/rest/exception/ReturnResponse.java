/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.exception;

import javax.ws.rs.core.Response;

/**
 *
 * @author edwigegédéon
 */
public class ReturnResponse {
    public static Response object(String msg,Object obj) {
            return Response.status(200).entity(msg+"\n"+obj).build();
	}
}
