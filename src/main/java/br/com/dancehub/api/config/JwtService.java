package br.com.dancehub.api.config;


import br.com.dancehub.api.contexts.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "5hrRU2SZpcVlZZ2V5TTrOsHTLSALNyMS5hrRU2SZpcVlZZ2V5TTrOsHTLSALNyMS" +
            "-GBqL23pdj-JLXKP3LJyjo" +
            "-KGdxqqtGca1w5hrRU2SZpcVlZZ2V5TTrOsHTLSALNyMSARWbfrRISFt62UxUgC55LsRzY9DYwdXK1NtZPyEmBp62POm_r2UsuDdYIQDQu7qYHOQxe89_4QRrpMLk8QIl9Y_z8qNJJHAS1273nag3ha-DwreqY43TY-rq6h1-3jSAATM4sGy4BhU1Lf370a0DTgJXgGqo7_HbB0034DrZkEzrvFeQL3dNuQB633FGoi3w1SEWFP0GvY2TVKg5kSyf_j2jG6v";


    public String generateToken(Map<String, Object> extraClaims, User userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getId().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(this.getSingInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateToken(User userDetails) {
        return this.generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, User userDetails) {
        final String uuid = this.extractUuid(token);
        if (userDetails.getPasswordDate() != null && userDetails.getPasswordDate().after(extractIssuedAt(token)))
            return false;
        return uuid.equals(userDetails.getId().toString()) && !isTokenExpired(token);
    }


    public String extractUuid(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(this.extractAllClaims(token));
    }

    private boolean isTokenExpired(String token) {
        return this.extractExpiration(token) != null
                && this.extractExpiration(token).before(Date.from(Instant.now()));
    }


    private Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    private Date extractIssuedAt(String token) {
        return extractAllClaims(token).getIssuedAt();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSingInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSingInKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
