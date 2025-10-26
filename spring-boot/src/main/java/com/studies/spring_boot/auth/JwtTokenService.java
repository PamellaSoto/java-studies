package com.studies.spring_boot.auth;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.studies.spring_boot.users.UserModel;

@Service
public class JwtTokenService {
  private static final String SECRET = "123456";

  private static final long EXPIRATION_MS = 24 * 60 * 60 * 1000;

  private final Algorithm algorithm;
  private final JWTVerifier verifier;

  public JwtTokenService() {
    this.algorithm = Algorithm.HMAC256(SECRET);
    this.verifier = JWT.require(algorithm).build();
  }
  public String generateToken(UserModel user) {
    return JWT.create()
            .withSubject(user.getId().toString())
            .withClaim("username", user.getUsername())
            .withClaim("name", user.getName())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .sign(algorithm);
  }

  public boolean validateToken(String token) {
    try {
        verifier.verify(token);
        return true;
    } catch (JWTVerificationException e) {
        return false;
    }
  }

  public String getUserIdFromToken(String token) {
    DecodedJWT decoded = verifier.verify(token);
    return decoded.getSubject();
  } 

    public String getUsernameFromToken(String token) {
        DecodedJWT decoded = verifier.verify(token);
        return decoded.getClaim("username").asString();
    }


}
