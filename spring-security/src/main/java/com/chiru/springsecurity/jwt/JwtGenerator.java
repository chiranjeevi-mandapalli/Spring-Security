package com.chiru.springsecurity.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * @author Chiranjeevi
 */
@Slf4j
@Component
public class JwtGenerator {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    public String generateToken(String userName, String userType) {
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationMs);
        String token = Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .claim("usertype",userType)
                .compact();
        return token;
    }

    private Key getSignKey(){
        byte [] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String getUserTypeFromJWT(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("usertype").toString();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            log.error("Invalid JWT Token: {}",e.getMessage());
        }catch (ExpiredJwtException e){
            log.error("JWT token is expired: {}",e.getMessage());
        }catch (UnsupportedJwtException e){
            log.error("JWT token is unsupported: {}",e.getMessage());
        }catch (IllegalArgumentException e){
            log.error("JWT claims string is empty: {}",e.getMessage());
        }catch (SignatureException e){
            log.error("Invalid JWT token: {}",e.getMessage());
        }
        return false;
    }
}
