package com.aziz.ecommercerestapi.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

  private String jwtSecret = "5705a82920cee05ca634e8a82a2a11be30aa0d655c7b87a67d48b828e8e46144";
  private Long jwtExpirationDate = 6000000L;

  // generate jwt token
  public String generateToken(Authentication authentication){
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
    String token = Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(expireDate)
            .signWith(key())
            .compact();

    return token;
  }

  private Key key(){
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
  }

  // get username from jwt token
  public String getUsername(String token){
    return Jwts.parser()
            .verifyWith((SecretKey) key())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
  }

  // validate jwt token
  public boolean validateToken(String token) {

    try {
      Jwts.parser()
              .verifyWith((SecretKey) key())
              .build()
              .parse(token);
      return true;
    } catch (MalformedJwtException exception){
      throw new RuntimeException("invalid JWT token");
    } catch (ExpiredJwtException exception){
      throw new RuntimeException("expired JWT token");
    } catch (UnsupportedJwtException exception){
      throw new RuntimeException("unsupported JWT token");
    } catch (IllegalArgumentException exception){
      throw new RuntimeException("JWT claims string is null or empty");
    }
  }
}
