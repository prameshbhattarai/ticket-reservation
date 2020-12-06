package com.ticket.reservation.dao;

import com.ticket.reservation.entities.ReservationUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author pramesh-bhattarai
 */
public interface ReservationUserRepository extends PagingAndSortingRepository<ReservationUser, Long> {

    @Query(value = "select u from ReservationUser u where u.username = :username")
    ReservationUser findByUsername(@Param("username") String username);
}
