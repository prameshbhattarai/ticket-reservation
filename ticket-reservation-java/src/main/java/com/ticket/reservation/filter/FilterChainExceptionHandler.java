package com.ticket.reservation.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.reservation.exception.ReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pramesh-bhattarai
 */
@Component
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (ReservationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            errorResponse.put("status",  Integer.toString(HttpStatus.UNAUTHORIZED.value()));
            errorResponse.put("message",  e.getMessage());

            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(errorResponse));
            out.flush();
            out.close();
        }
    }
}
