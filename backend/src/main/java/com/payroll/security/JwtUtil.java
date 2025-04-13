package com.payroll.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email, String role) {
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token) {
        try {
            token = validateTokenFormat(token); // Pre-validate token format
            return Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token) // Validate signature
                    .getBody()
                    .getSubject();
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to extract email from token: " + e.getMessage(), e);
        }
    }

    public String extractRole(String token) {
        try {
            token = validateTokenFormat(token); // Pre-validate token format
            return (String) Jwts.parserBuilder()
                    .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token) // Validate signature
                    .getBody()
                    .get("role");
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to extract role from token: " + e.getMessage(), e);
        }
    }


    /**
     * Basic validation for token format to avoid illegal base64 character errors.
     */
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

