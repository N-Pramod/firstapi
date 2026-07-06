package com.pramod.firstapi.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final SecretKey SECRET_KEY =
            Keys.secretKeyFor(
                    io.jsonwebtoken.SignatureAlgorithm.HS256);

    public static String generateToken(
            String username) {

        return Jwts.builder()

                .subject(username)

                .issuedAt(new Date())

                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 3600000))

                .signWith(SECRET_KEY)

                .compact();
    }

    public static String extractUsername(
            String token) {

        return Jwts.parser()

                .verifyWith(SECRET_KEY)

                .build()

                .parseSignedClaims(token)

                .getPayload()

                .getSubject();
    }
}