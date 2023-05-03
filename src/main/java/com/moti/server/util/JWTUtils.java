package com.moti.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moti.server.constans.Consts;
import com.moti.server.dto.SuccessfulLoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.*;
//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;


import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

//import static com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm.*;//
//import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class JWTUtils {

    public static Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(Consts.JWT_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }
     /*
    public static String createJWT(SuccessfulLoginDto userLoginData) {

        if(userLoginData.getUserType()== UserTypes.company){
            return createJWT(userLoginData.getId().toString(), userLoginData.getUserType().toString(), userLoginData.getFirstName(), userLoginData.getCompanyId().toString(),0);
        }
        else {
            return createJWT(userLoginData.getId().toString(), userLoginData.getUserType().toString(), userLoginData.getFirstName(), null,0);
        }

        return createJWT()
    }
        */


    public static String createJWT(SuccessfulLoginDto userLoginData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        //Converting the user data into JSON string
        String  jsonLoginDetails = objectMapper.writeValueAsString(userLoginData);
        String id= String.valueOf(userLoginData.getId());
        String userType= String.valueOf(userLoginData.getUserType());
        String  companyId= String.valueOf(userLoginData.getCompanyId());
        String token = createJWT(id,userType,jsonLoginDetails,companyId,0);
        return token;
    }


    private static String createJWT(String id, String userType, String subject ,String  companyId, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm =SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Consts.JWT_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(userType)
                .setAudience(String.valueOf(companyId))
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    public static Integer getIdByToken(String token) {
        Claims claims = decodeJWT(token);
        return Integer.parseInt(claims.getId());
    }

    public static Integer validateToken(String token) throws Exception {
        Claims claims = decodeJWT(token);
        return Integer.parseInt(claims.getId());
    }
}



