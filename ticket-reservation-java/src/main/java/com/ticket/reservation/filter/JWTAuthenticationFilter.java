package com.ticket.reservation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.reservation.dto.ReservationUserDto;
import com.ticket.reservation.entities.ReservationUser;
import com.ticket.reservation.security.ReservationUserDetails;
import com.ticket.reservation.security.SecurityConstants;
import com.ticket.reservation.utils.JsonUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author pramesh-bhattarai
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Object reservationUser = new ObjectMapper()
                    .readValue(req.getInputStream(), ReservationUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            ((ReservationUser) reservationUser).getUsername(),
                            ((ReservationUser) reservationUser).getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWTUtils.createToken(auth);
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        String username = ((ReservationUserDetails) auth.getPrincipal()).getUsername();
        long userId = ((ReservationUserDetails) auth.getPrincipal()).getReservationUser().getId();

        ReservationUserDto user = new ReservationUserDto();
        user.setId(userId);
        user.setUsername(username);

        res.getWriter().write(JsonUtils.writeObjectAsString(user));
        res.getWriter().flush();
    }
}
