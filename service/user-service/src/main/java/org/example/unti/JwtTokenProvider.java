package org.example.unti;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {
    private String secretKey = "c2JXb0pGd3lQVGpMeHhGR29VbnNpVnRSbVdWcFZrV1dCZGtWdExFb0lKVGhSZGNRVFlwZ0lJ";  // 密钥，实际应用中应该放在配置文件中
    private long validityInMilliseconds = 3600000; // 1小时过期时间

    public String createToken(UserEntity user) {
        Claims claims = Jwts.claims().setId(String.valueOf(UUID.randomUUID())).setSubject(String.valueOf(user.getId())); // 用户id作为主体
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
