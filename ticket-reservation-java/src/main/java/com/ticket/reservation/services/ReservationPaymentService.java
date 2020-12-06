package com.ticket.reservation.services;

import com.ticket.reservation.dto.PaginationResponse;
import com.ticket.reservation.dto.ReservationPaymentDto;

/**
 * @author pramesh-bhattarai
 */
public interface ReservationPaymentService {

    ReservationPaymentDto createPaymentForReservation(ReservationPaymentDto reservationPaymentDto);

    PaginationResponse<ReservationPaymentDto> getAllReservationPayment(Integer pageNo, Integer pageSize);
}
