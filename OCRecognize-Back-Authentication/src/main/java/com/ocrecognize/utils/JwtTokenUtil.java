package com.ocrecognize.utils;

import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenUtil {

    public static String generateToken(String username, List<String> roles){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return doGenerateToken(claims, username, Constants.AUDIENCE_WEB);
    }

    public static String getUsernameFromToken(String token) throws ParseException {
        return SignedJWT.parse(token).getJWTClaimsSet().getSubjectClaim();
    }

    private static String doGenerateToken(Map<String, Object> claims, String username, String audienceWeb) {

        final Date createdDate = new Date();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setAudience(audienceWeb)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET)
                .compact();
    }

    private static Date calculateExpirationDate(Date createdDate){
        return new Date(createdDate.getTime() + Constants.JWT_EXPIRATION * 1000);
    }
}
