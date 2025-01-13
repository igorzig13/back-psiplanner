package web2.dev.backpsiplanner.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import web2.dev.backpsiplanner.model.Role;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JWTCreator {
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject){

        byte[] keyBytes = Decoders.BASE64.decode(key);
        Key newKey = Keys.hmacShaKeyFor(keyBytes);

        String token = Jwts.builder()
                .subject(jwtObject.getSubject())
                .issuedAt(jwtObject.getIssuedAt())
                .expiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, jwtObject.getRoleNames())
                .signWith(newKey).compact();

        return prefix + " " + token;
    }

    public static JWTObject create(String token, String prefix, String key) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        SecretKey secretKey = Keys.hmacShaKeyFor(keyBytes);

        JWTObject jwtObject = new JWTObject();
        token = token.replace(prefix + " ", "");
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
        jwtObject.setSubject(claims.getSubject());
        jwtObject.setIssuedAt(claims.getIssuedAt());
        jwtObject.setExpiration(claims.getExpiration());

        @SuppressWarnings("unchecked")
        Set<String> roleNames = new HashSet<>((Collection<? extends String>) claims.get(ROLES_AUTHORITIES));
        jwtObject.setRoles(roleNames.stream().map(Role::new).collect(Collectors.toSet()));

        return jwtObject;
    }
}
