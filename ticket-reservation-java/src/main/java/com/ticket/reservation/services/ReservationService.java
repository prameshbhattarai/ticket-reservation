package com.ticket.reservation.services;

import com.ticket.reservation.dto.PaginationResponse;
import com.ticket.reservation.dto.ReservationDto;

/**
 * @author pramesh-bhattarai
 */
public interface ReservationService {

    ReservationDto createReservation(ReservationDto reservationDto);

    PaginationResponse<ReservationDto> getAllReservation(Integer pageNo, Integer pageSize);
}
