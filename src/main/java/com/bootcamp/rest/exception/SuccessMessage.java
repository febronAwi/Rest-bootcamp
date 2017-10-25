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
public class SuccessMessage {
    public static Response message(String msg) {
            return Response.status(200).entity(msg).build();
	}
}
