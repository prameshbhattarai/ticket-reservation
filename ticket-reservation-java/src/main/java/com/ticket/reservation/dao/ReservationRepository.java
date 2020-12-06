package com.ticket.reservation.dao;

import com.ticket.reservation.entities.Reservation;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author pramesh-bhattarai
 */
public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {
}
