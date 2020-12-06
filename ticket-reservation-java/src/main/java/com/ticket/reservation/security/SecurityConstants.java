package com.ticket.reservation.security;

/**
 * @author pramesh-bhattarai
 */
public final class SecurityConstants {
    public static final String SECRET = "ReservationSecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 3600_000; // 1 hour
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}

