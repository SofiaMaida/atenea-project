package ar.com.ada.atenea.component.security;

import ar.com.ada.atenea.model.dto.security.JwtUserDetails;
import ar.com.ada.atenea.model.entity.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component("jwtAuthProvider")
public class JwtAuthProvider {
    //Ayuda a manejar todo desde el servicio

    @Value("${auth.jwt.secret.seed}")
    private String secretSeed;

    @Value("${auth.jwt.expiration.time}")
    private Integer expirationTime;

    @Value("${auth.jwt.type}")
    private String authJwtType;

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "create_at";

    //Crear usuario especial
    public JwtUserDetails createJwtUserDetails(User user) {
        //Convertir autoridades para que el core-security entienda
        Set<SimpleGrantedAuthority> authoritySet = user.getAuthorities().stream()
                //authority.getName().name() => trae el nombre y al ser "enum" tiene que trae el nombre del enum
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toSet());

        JwtUserDetails jwtUserDetails = new JwtUserDetails(
                user.getId(),
                user.getUsername(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.getEmail(),
                authoritySet, user.getEnabled()
        );
        return jwtUserDetails;
    }

    //Metodo que genere el token
    public String doGenerateToken(UserDetails userDetails) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) userDetails;

        //Playload: data jwt.io -> definir info
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, jwtUserDetails.getUsername());
        claims.put("first_name", jwtUserDetails.getFirstName());
        claims.put("last_name", jwtUserDetails.getLastName());
        claims.put("roles", jwtUserDetails.getAuthorities());
        claims.put(CLAIM_KEY_CREATED, new Date());

        //Crea el token
        return Jwts.builder()
                //Inserto valores: del mapa "claims"
                .setClaims(claims)
                .setSubject(jwtUserDetails.getUsername())
                //Toma el tiempo en sistema en milisegundos
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //Tiempo de expiracion definido en el properties
                .setExpiration(generateExpirationDate())
                //Tipo de codificacion: HS512 (pag Jwt.io), y la codificacion del properties
                .signWith(SignatureAlgorithm.HS512, secretSeed)
                //Generacion del token
                .compact();
        //Genera el String representado en Jwt.io
    }

    private Date generateExpirationDate() {
        //Crea un objeto con el tiempo actual + la expiracion del tiempo:
        //Toma el tiempo actual y le da 5 min mas para que se expire
        Date date = new Date(System.currentTimeMillis() + expirationTime);

        return date;
    }

    //Validacion de si el token que se recibe en las peticiones es de tipo Bearer
    public boolean isBearer(String authorization) {
        //El token no sea nulo
        return authorization != null &&
                //Empiece por el tipo de autenticacion
                authorization.startsWith(authJwtType) &&
                //Se divida en donde haya un "." lo divida en 3
                authorization.split("\\.").length == 3;
    }

    //Validar el token (puede que el tipo sea ok, pero que se haya expirado)
    public Boolean validateToken(String token, UserDetails userDetails) {
        //Castea el usuario para ocnvertirlo en un JwtUserDetails
        JwtUserDetails jwtUserDetails = (JwtUserDetails) userDetails;
        //De ese token trae el nombre
        final String username = getUsernameFromToken(token);

        return ( username.equals(jwtUserDetails.getUsername()) && !isTokenExpired(token));
    }


    public String getUsernameFromToken(String token) {
        String username;
        try {
            token = token.trim().replace(authJwtType, "");
            username = getClaimsOfToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Claims getClaimsOfToken(String token) {
        final Claims claims = getClaimsFromToken(token);
        return claims;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            token = token.trim().replace(authJwtType, "");
            expirationDate = getClaimsOfToken(token).getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }


    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretSeed)
                .parseClaimsJws(token)
                .getBody();
    }

}
