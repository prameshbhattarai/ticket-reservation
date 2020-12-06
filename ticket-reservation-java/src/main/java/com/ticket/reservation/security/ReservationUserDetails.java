package com.ticket.reservation.security;

import com.ticket.reservation.entities.ReservationUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author pramesh-bhattarai
 */
@Getter
@Setter
public class ReservationUserDetails extends User {

    private final ReservationUser reservationUser;

    public ReservationUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, ReservationUser user) {
        super(username, password, authorities);
        this.reservationUser = user;
    }
}
