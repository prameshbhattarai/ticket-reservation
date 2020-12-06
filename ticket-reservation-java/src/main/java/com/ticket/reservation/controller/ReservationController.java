package com.ticket.reservation.controller;

import com.ticket.reservation.dto.PaginationResponse;
import com.ticket.reservation.dto.ReservationDto;
import com.ticket.reservation.services.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author pramesh-bhattarai
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<ReservationDto>> getAllReservation(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PaginationResponse<ReservationDto> paginationResponse = reservationService.getAllReservation(pageNo, pageSize);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return ResponseEntity.ok().body(createdReservation);
    }
}
