package com.ticket.reservation.services;

import com.ticket.reservation.entities.ReservationUser;
import org.springframework.security.core.Authentication;

/**
 * @author pramesh-bhattarai
 */
public interface AuthenticationFacade {

    Authentication getAuthentication();

    ReservationUser getCurrentUser();

    long getCurrentUserId();
}
