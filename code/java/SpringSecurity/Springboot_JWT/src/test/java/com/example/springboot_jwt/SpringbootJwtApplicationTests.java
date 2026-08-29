package com.example.springboot_jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;

// @SpringBootTest
class SpringbootJwtApplicationTests {
    private static String secret_key = "zsh_coding";
    private static String token = null;

    @Test
    void createJwtTest() {
        HashMap<String, Object> userClaims = new HashMap<>();
        userClaims.put("userId", "001");
        userClaims.put("username", "帅");
        token = JWT.create()
                .withClaim("user", userClaims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .sign(Algorithm.HMAC256(secret_key));
        System.out.println(token);

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret_key)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        System.out.println(decodedJWT.getClaim("user").asMap());
    }

}
