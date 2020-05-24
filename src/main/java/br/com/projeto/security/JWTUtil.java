package br.com.projeto.security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

    private static final String key = "SECRET_TOKEN";
    private static final TemporalAmount TOKEN_VALIDITY = Duration.ofHours( 4L );
    
    public static final String TOKEN_HEADER = "Authorization";
    
    public static String create( String name, String role ) {
    	final Instant now = Instant.now();
        final Date expiryDate = Date.from( now.plus( TOKEN_VALIDITY ) );
        
        return Jwts.builder()
                .setSubject( name )
                .claim( "role", role )
                .setExpiration( expiryDate )
                .setIssuedAt( Date.from( now ) )
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public static Jws<Claims> decode( final String token ){
        return Jwts.parser().setSigningKey(key).parseClaimsJws( token );
    }
}
