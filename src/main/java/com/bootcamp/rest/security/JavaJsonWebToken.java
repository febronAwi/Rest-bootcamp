/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.rest.security;

import com.sun.javafx.scene.traversal.Algorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.util.Date; 
public class JavaJsonWebToken {
    
    String key = "bootkey";
    
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    
//Sample method to construct a JWT
public String createJWT(String issuer, String subject, long ttlMillis) {
    
    //The JWT signature algorithm we will be using to sign the token
        //SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    long nowMillis = System.currentTimeMillis();
    Date now = new Date(nowMillis);
    /*
    Date exp = new Date(nowMillis+ttlMillis);
    
    String compactJws = Jwts.builder()
          .setSubject(subject)
          .setExpiration(exp)
          .signWith(SignatureAlgorithm.HS512, key)
          .compact();
    Date exp = now. +ttlMillis;*/

    //We will sign our JWT with our ApiKey secret
    
        //byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        //Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //Let's set the JWT Claims
    JwtBuilder builder = Jwts.builder()
                                .setIssuedAt(now)
                                .setIssuer(issuer)
                                .setSubject(subject)
                                .signWith(signatureAlgorithm, signingKey);    

    //if it has been specified, let's add the expiration
    if (ttlMillis >= 0) {
    long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
    }
    

    //Builds the JWT and serializes it to a compact, URL-safe string
    
    return builder.compact();
}

 //Sample method to validate and read the JWT 
 public String   parseJWT ( String token )   { 
     
     //This line will throw an exception if it is not a signed JWS (as expected) 
     Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
     /*
     Claims  claims   =   Jwts.parser ()          
        . setSigningKey ( DatatypeConverter.parseBase64Binary ( secretkey ) ) 
        . parseClaimsJws (token).getBody () ; 
     System . out . println ( "key: "   +   signingKey ) ; 
     System . out . println ( "Subject: "   +   claims.getBody() . getSubject ( ) ) ; 
     System . out . println ( "Issuer: "   +   claims.getBody() . getIssuer ( ) ) ; 
     System . out . println ( "Expiration: "   +   claims.getBody() . getExpiration ( ) ) ; 

     /*   
        try {
    Algorithm algorithm = Algorithm.HS256(key);
    JWTVerifier verifier = JWT.require(algorithm)
        .withIssuer("auth0")
        .build(); //Reusable verifier instance
    DecodedJWT jwt = verifier.verify(token);
} catch (UnsupportedEncodingException exception){
    //UTF-8 encoding not supported
} catch (JWTVerificationException exception){
    //Invalid signature/claims
}*/
     return token;
 } 
 

 
}
