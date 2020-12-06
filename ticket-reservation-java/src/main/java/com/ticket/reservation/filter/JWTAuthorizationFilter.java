package com.ticket.reservation.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ticket.reservation.security.ReservationUserDetails;
import com.ticket.reservation.security.SecurityConstants;
import com.ticket.reservation.services.ReservationUserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author pramesh-bhattarai
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final ReservationUserDetailsService reservationUserDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, ReservationUserDetailsService reservationUserDetailsService) {
        super(authenticationManager);
        this.reservationUserDetailsService = reservationUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(SecurityConstants.HEADER_STRING);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = this.getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstants.HEADER_STRING);
        // parse the token.
        DecodedJWT decodedJWT = JWTUtils.validateToken(token);

        // get reservationUser by username
        ReservationUserDetails reservationUserDetails = (ReservationUserDetails) reservationUserDetailsService.loadUserByUsername(decodedJWT.getSubject());

        if (reservationUserDetails != null) {
            return new UsernamePasswordAuthenticationToken(reservationUserDetails, null, null);
        }
        return null;
    }
}
