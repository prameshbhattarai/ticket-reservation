package com.ticket.reservation.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ticket.reservation.exception.ReservationException;
import com.ticket.reservation.security.ReservationUserDetails;
import com.ticket.reservation.security.SecurityConstants;
import org.springframework.security.core.Authentication;

import java.util.Date;

/**
 * @author pramesh-bhattarai
 */
public final class JWTUtils {

    private JWTUtils() {
    }

    public static String createToken(Authentication auth) {
        return JWT.create()
                .withSubject(((ReservationUserDetails) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
    }

    public static DecodedJWT validateToken(String token) {
        if (token == null) {
            throw new ReservationException("Unable to find token in request header.");
        }
        try {
            return JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""));
        } catch (TokenExpiredException e) {
            throw new ReservationException("Token is already expired.");
        } catch (JWTVerificationException e) {
            throw new ReservationException("Invalid token provided.");
        }
    }

}
