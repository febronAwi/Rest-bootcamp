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
public class TokenNotGenerateException {
    public static Response generateTokenException() {
            return Response.status(409).entity("Le token n'a pu etre génére !").build();
	}
}
