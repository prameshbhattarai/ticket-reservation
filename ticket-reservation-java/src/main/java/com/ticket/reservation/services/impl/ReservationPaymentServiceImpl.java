package com.ticket.reservation.services.impl;

import com.ticket.reservation.dao.ReservationPaymentRepository;
import com.ticket.reservation.dao.ReservationRepository;
import com.ticket.reservation.dto.PaginationResponse;
import com.ticket.reservation.dto.ReservationPaymentDto;
import com.ticket.reservation.entities.Reservation;
import com.ticket.reservation.entities.ReservationPayment;
import com.ticket.reservation.exception.ReservationException;
import com.ticket.reservation.services.ReservationPaymentService;
import com.ticket.reservation.utils.EntityToDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author pramesh-bhattarai
 */
@Service
public class ReservationPaymentServiceImpl implements ReservationPaymentService {

    private final ReservationPaymentRepository reservationPaymentRepository;
    private final ReservationRepository reservationRepository;

    public ReservationPaymentServiceImpl(ReservationPaymentRepository reservationPaymentRepository, ReservationRepository reservationRepository) {
        this.reservationPaymentRepository = reservationPaymentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationPaymentDto createPaymentForReservation(ReservationPaymentDto reservationPaymentDto) {
        long reservationId = reservationPaymentDto.getReservation().getId();
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {

            if (reservation.get().getAmount() != reservationPaymentDto.getPaymentAmount()) {
                throw new ReservationException("Payment amount not matched, found: " + reservationPaymentDto.getPaymentAmount() + " should be: " + reservation.get().getAmount());
            }

            ReservationPayment reservationPayment = reservationPaymentRepository.save(mapReservationPaymentDtoToReservationPayment(reservationPaymentDto, reservation.get()));
            return EntityToDtoMapper.mapReservationPaymentToReservationPaymentDto(reservationPayment);
        } else {
            throw new ReservationException("Unable to get reservation with " + reservationId);
        }
    }

    @Override
    public PaginationResponse<ReservationPaymentDto> getAllReservationPayment(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ReservationPayment> pagedResult = reservationPaymentRepository.findAll(paging);

        PaginationResponse<ReservationPaymentDto> paginationResponse = new PaginationResponse<>();
        if (pagedResult.hasContent()) {
            List<ReservationPaymentDto> paymentDtos = EntityToDtoMapper.mapReservationPaymentListToReservationPaymentDtoList(pagedResult.getContent());
            paginationResponse.setData(paymentDtos);
            paginationResponse.setTotalCount(pagedResult.getTotalElements());
        }
        return paginationResponse;
    }

    private ReservationPayment mapReservationPaymentDtoToReservationPayment(ReservationPaymentDto paymentDto, Reservation reservation) {
        ReservationPayment payment = new ReservationPayment();
        payment.setPaymentAmount(paymentDto.getPaymentAmount());
        payment.setReservation(reservation);
        return payment;
    }
}
