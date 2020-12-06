package com.ticket.reservation.services.impl;

import com.ticket.reservation.dao.ReservationUserRepository;
import com.ticket.reservation.entities.ReservationUser;
import com.ticket.reservation.security.ReservationUserDetails;
import com.ticket.reservation.services.ReservationUserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author pramesh-bhattarai
 */
@Service
public class ReservationUserDetailsServiceImpl implements ReservationUserDetailsService {

    private final ReservationUserRepository userRepository;

    public ReservationUserDetailsServiceImpl(ReservationUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ReservationUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ReservationUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new ReservationUserDetails(applicationUser.getUsername(), applicationUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("role")), applicationUser);
    }

}
