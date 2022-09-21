package com.ocrecognize.utils;

import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

public class JwtTokenUtil {

    public static List<String> getRolesFromToken(String token){
        Function<Claims, List<String>> roles = a -> a.get("roles", List.class);
        return getClaimFromToken(token, roles);
    }

    public static String generateToken(String username, String role){
        Map<String, Object> claims = new HashMap<>();
        List<String> roleList = new ArrayList<>();
        roleList.add(role);
        claims.put("roles", roleList);

        return doGenerateToken(claims, username, Constants.AUDIENCE_WEB);
    }

    public static String getUsernameFromToken(String token) throws ParseException {
        return SignedJWT.parse(token).getJWTClaimsSet().getSubjectClaim();
    }

    public static String doGenerateToken(Map<String, Object> claims, String username, String audienceWeb) {

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

    public static Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public static Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsTFunction){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsTFunction.apply(claims);
    }

    public static Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(Constants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public static Date calculateExpirationDate(Date createdDate){
        return new Date(createdDate.getTime() + Constants.JWT_EXPIRATION * 1000);
    }
}
