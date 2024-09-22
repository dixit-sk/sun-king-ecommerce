package com.sunking.ecommerce.order.utils;

import com.sunking.ecommerce.order.expception.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
        } catch (Exception ex) {
            throw new BadRequestException(ex.getMessage());
        }
        return true;
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getSubject(String token) {
        if (validateToken(token))
            return getClaims(token).getSubject();
        return null;
    }

}
