package hoop.api.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import hoop.api.api.domain.user.entity.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    private static final String secret = "secret-hoop.co";

    public String generateToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("api-hoop")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expiresAt())
                    .withClaim("userId", user.getId())
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("JWT generation failed");
        }

    }


    public Long getUserIdFromToken(String token) {

        try {

            if(token.startsWith("Bearer ")){
                token = token.replace("Bearer ","");
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("api-hoop")
                    .build()
                    .verify(token)
                    .getClaim("userId")
                    .asLong();

        }catch (JWTVerificationException ex){
            throw new RuntimeException("JWT verification failed userid" + ex.getMessage());
        }
    }


    public String getSubject(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("api-hoop")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            throw new RuntimeException("JWT verification failed");
        }


    }

    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
