package com.ticket.reservation.services.impl;

import com.ticket.reservation.entities.ReservationUser;
import com.ticket.reservation.security.ReservationUserDetails;
import com.ticket.reservation.services.AuthenticationFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author pramesh-bhattarai
 */
@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public ReservationUser getCurrentUser() {
        return ((ReservationUserDetails) this.getAuthentication().getPrincipal()).getReservationUser();
    }

    @Override
    public long getCurrentUserId() {
        return this.getCurrentUser().getId();
    }
}
