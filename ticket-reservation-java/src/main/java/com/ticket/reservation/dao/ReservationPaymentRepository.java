package com.ticket.reservation.dao;

import com.ticket.reservation.entities.ReservationPayment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author pramesh-bhattarai
 */
public interface ReservationPaymentRepository extends PagingAndSortingRepository<ReservationPayment, Long> {
}
