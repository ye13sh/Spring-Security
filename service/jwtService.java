package com.ye13sh.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtService {
//    @Value("$('jwt.secret)")
    private static final String secret="ILrTFpoKLQE1+E0WjlhpKo3j1+5gpdWOD/FHuHWcyn4=";
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String generateToken(String username){
            Map<String, Object> claims = new HashMap<>();
            return CreateToken(claims, username);
    }


    private String CreateToken(Map<String,Object> claims, String username){
//        Authority authority=new Authority();
//        if (authority.isEnabled()==true) {
//            return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
//                    .setExpiration(new Date(System.currentTimeMillis()+1000*60*30)).signWith(getKey(), SignatureAlgorithm.HS256).compact();
//        }else {
//             throw new RuntimeException("User has no Authorization to login!/User has been Suspended!!!");
//        }

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis()+1000*60*30)).signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private static Key getKey() {
        byte[] decodedKey=Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(decodedKey);
    }

}