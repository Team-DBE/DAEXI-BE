package com.example.daexi.global.security.jwt;

import com.example.daexi.global.security.auth.CustomUserDetails;
import com.example.daexi.global.security.auth.CustomUserDetailsService;
import com.example.daexi.global.security.exception.JwtExpiredException;
import com.example.daexi.global.security.exception.JwtInvalidException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final CustomUserDetailsService customUserDetailsService;
    private final static String ACCESS_TOKEN="access_token";
    private final RedisTemplate<String,String> redisTemplate;
    private final static String REFRESH_TOKEN="refresh_token";
    private final static String REDIS_PREFIX = "REFRESH_TOKEN:";
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public void deleteRefreshToken(String accountId) {
        String key = REDIS_PREFIX + accountId;
        redisTemplate.delete(key);
    }

    public String generateAccessToken(String accountId) {
        return generateToken(accountId,ACCESS_TOKEN, jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(String accountId) {
        String refreshToken = generateToken(accountId,REFRESH_TOKEN, jwtProperties.getRefreshExp());
        String key = REDIS_PREFIX + accountId;
        redisTemplate.opsForValue().set(key, refreshToken, jwtProperties.getRefreshExp());
        return refreshToken;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims claims = getClaims(token);
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }

    private String generateToken(String accountId, String type, Long time) {
        Date now = new Date();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setSubject(accountId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+time))
                .setHeaderParam("typ",type)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException();
        } catch(JwtException e) {
            throw new JwtInvalidException();
        }
    }

    public void validateRefreshToken(String refreshToken, String accountId) {
        String key = REDIS_PREFIX + accountId;
        String storedRefreshToken = redisTemplate.opsForValue().get(key);

        if(storedRefreshToken == null) throw new JwtInvalidException();

        if(!storedRefreshToken.equals(refreshToken)) throw new JwtInvalidException();

        validateToken(refreshToken);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken!=null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Claims getClaims(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch(ExpiredJwtException e) {
            throw new JwtExpiredException();
        } catch(JwtException e) {
            throw new JwtInvalidException();
        }
    }

    public String reissueAccessToken(String refreshToken, String accountId) {
        validateRefreshToken(refreshToken, accountId);

        return generateAccessToken(accountId);
    }
}
