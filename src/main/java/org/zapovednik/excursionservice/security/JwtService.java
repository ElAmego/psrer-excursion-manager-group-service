package org.zapovednik.excursionservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final RSAPublicKey publicKey;

    public JwtService(@Value("${jwt.public-key}") final Resource publicKeyResource) throws Exception {
        this.publicKey = loadPublicKey(publicKeyResource);
    }

    public String extractLogin(final String token) {
        return extractAllClaims(token).getSubject();
    }

    public Long extractUserId(final String token) {
        return extractAllClaims(token).get("userId", Long.class);
    }

    public String extractUserRole(final String token) {
        return extractAllClaims(token).get("userRole", String.class);
    }

    public boolean isTokenValid(final String token) {
        return !isTokenExpired(token);
    }

    private boolean isTokenExpired(final String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(final String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private RSAPublicKey loadPublicKey(final Resource resource) throws Exception {
        final String key = new String(resource.getInputStream().readAllBytes())
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        final byte[] keyBytes = Base64.getDecoder().decode(key);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
    }
}