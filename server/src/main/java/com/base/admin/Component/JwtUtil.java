package com.base.admin.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {
  @Value("${jwt.expiration}")
  private Long expirationMillis;
  private SecretKey secretKey;

  @Autowired
  public JwtUtil(Environment env) {
    try {
      String secretKeyString = env.getProperty("jwt.secret");
      byte[] decodedKey = Base64.getDecoder().decode(secretKeyString);
      this.secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    } catch (Exception e) {
      System.out.println("Error getting jwt.secret or jwt.expiration: " + e.getMessage());
    }
  }

  public String extractUserId(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
  }

  public String generateToken(String userId) {
    long currentTimeMillis = System.currentTimeMillis();
    Date issuedAt = new Date(currentTimeMillis);
    Date expiration = new Date(currentTimeMillis + expirationMillis);

    return Jwts.builder()
        .subject(userId)
        .issuedAt(issuedAt)
        .expiration(expiration)
        .signWith(secretKey)
        .compact();
  }

  public Boolean isTokenExpired(String token) {
    Date expiration = extractExpiration(token);
    return expiration.before(new Date());
  }

}
