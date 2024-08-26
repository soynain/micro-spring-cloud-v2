package com.usermicroservice.main.Components;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGenerator {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Value("${jwt.private-key-path}")
    private String privateKeyPath;

    @Value("${jwt.expiration}")
    private int jwtExpirationInMs;

    private PrivateKey loadPrivateKey() throws Exception {

        String keyString = new String(Files.readAllBytes(Paths.get(privateKeyPath)));

        // Remove the PEM header and footer
        keyString = keyString.replaceAll("-----BEGIN PRIVATE KEY-----", "")
                             .replaceAll("-----END PRIVATE KEY-----", "")
                             .replaceAll("\\s", "");
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMs))
                    .signWith(loadPrivateKey(), SignatureAlgorithm.RS256)
                    .compact();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            throw new RuntimeException("Error al generar el JWT", e);
        }
    }
}
