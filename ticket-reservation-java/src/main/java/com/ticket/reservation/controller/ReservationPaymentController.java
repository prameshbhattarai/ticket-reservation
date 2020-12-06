package com.ticket.reservation.controller;

import com.ticket.reservation.dto.PaginationResponse;
import com.ticket.reservation.dto.ReservationPaymentDto;
import com.ticket.reservation.services.ReservationPaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author pramesh-bhattarai
 */
@RestController
@RequestMapping("/payment")
public class ReservationPaymentController {

    private final ReservationPaymentService reservationPaymentService;

    public ReservationPaymentController(ReservationPaymentService reservationPaymentService) {
        this.reservationPaymentService = reservationPaymentService;
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<ReservationPaymentDto>> getAllReservationPayment(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PaginationResponse<ReservationPaymentDto> paginationResponse = reservationPaymentService.getAllReservationPayment(pageNo, pageSize);
        return ResponseEntity.ok().body(paginationResponse);
    }

    @PostMapping
    public ResponseEntity<ReservationPaymentDto> createReservationPayment(@Valid @RequestBody ReservationPaymentDto reservationPaymentDto) {
        ReservationPaymentDto createdPayment = reservationPaymentService.createPaymentForReservation(reservationPaymentDto);
        return ResponseEntity.ok().body(createdPayment);
    }
}
