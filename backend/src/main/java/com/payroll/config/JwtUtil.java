package com.payroll.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.payroll.model.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;



@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs;
   
    // Method to generate a JWT token
    public String generateToken(String username, Set<Role> role) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role.stream().map(Role::name).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs)) 
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Method to extract username from JWT token
    public String extractUsername(String token) {
        try {
            token = validateTokenFormat(token); // Pre-validate token format
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token) // Validate signature
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to extract username from token: " + e.getMessage(), e);
        }
    }

    // Method to extract role from JWT token
    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        try {
            token = validateTokenFormat(token);
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("role", List.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to extract roles from token: " + e.getMessage(), e);
        }
    }


    // Method to validate the JWT token
    public boolean validateToken(String token) {
        try {
            token = validateTokenFormat(token); // Pre-validate token format
            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token); // Validate signature
            return true;
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JWT token: " + e.getMessage(), e);
        }
    }
    
    // Method to validate the format of the JWT token
    private String validateTokenFormat(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("JWT token is missing or empty.");
        }
        if (!token.contains(".") || token.split("\\.").length != 3) {
            throw new IllegalArgumentException("Invalid JWT token format. Must have 3 parts: header.payload.signature");
        }
        return token;
    }
    
}
