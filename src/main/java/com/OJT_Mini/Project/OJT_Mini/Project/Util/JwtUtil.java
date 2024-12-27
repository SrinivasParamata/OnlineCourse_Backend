package com.OJT_Mini.Project.OJT_Mini.Project.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.util.Date;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private SecretKey secretKey;
    private static final int TOKEN_VALIDITY = 120 * 1000;

    @jakarta.annotation.PostConstruct
    public void init() {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); 
    }

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(secretKey)  
                .compact();
    }

    // Validate Token
    public boolean validateToken(String token, String username) {
        try {
            Claims claims = getClaims(token);
            return username.equals(claims.getSubject()) && !isTokenExpired(claims);
        } catch (JwtException e) {
            return false; // Invalid token or any JWT exception
        }
    }


//    public String getUsernameFromToken(String token) {
//        return getClaims(token).getSubject();
//    }

    // Check if Token is expired
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder() 
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
