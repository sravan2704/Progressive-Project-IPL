package com.wecp.progressive.jwt;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.wecp.progressive.entity.User;
import com.wecp.progressive.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

// new imported

// import io.jsonwebtoken.security.Keys;
// import java.nio.charset.StandardCharsets;
// import java.security.Key;
// import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtil {

    private UserRepository userRepository;

    @Autowired
    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final String SECRET_KEY = "secretKey000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    
    private final int expiration = 86400;
    
    public String generateToken(String username)
    {
        Date now = new Date();

        Date expiryDate = new Date(now.getTime() + expiration *1000);
        User user = userRepository.findByUsername(username);

        Map<String,Object> claims = new HashMap<>();

        claims.put("sub", username);

        claims.put("role", user.getRole());

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
            .compact();
    }

    public Claims extractAllClaims(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
                
        return claims;

    }

    public String extractusername(String token)
    {
        String username = Jwts.parser()
                .setSigningKey(SECRET_KEY) 
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
                
        return username;
    }

    public Boolean isTokenExpired(String token)
    {
        Date tokenExpirationTime =Jwts.parser()
            .setSigningKey(SECRET_KEY)  
            .parseClaimsJws(token)
            .getBody()
            .getExpiration();

        if(tokenExpirationTime.before(new Date()))
        {
            return true;
        }
        return false;
    }
    public Boolean validateToken(String token,UserDetails userDetails)
    {
        String username  = extractusername(token);

        if(username.equals(userDetails.getUsername()) && !isTokenExpired(token))
        {
            return true;
        }
        return false;
    }

   
}