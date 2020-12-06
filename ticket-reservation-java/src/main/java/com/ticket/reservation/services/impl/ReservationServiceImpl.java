package com.ticket.reservation.services.impl;

import com.ticket.reservation.dao.ReservationRepository;
import com.ticket.reservation.dto.PaginationResponse;
import com.ticket.reservation.dto.ReservationDto;
import com.ticket.reservation.entities.Reservation;
import com.ticket.reservation.services.AuthenticationFacade;
import com.ticket.reservation.services.ReservationService;
import com.ticket.reservation.utils.EntityToDtoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pramesh-bhattarai
 */
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AuthenticationFacade authentication;

    public ReservationServiceImpl(ReservationRepository reservationRepository, AuthenticationFacade authentication) {
        this.reservationRepository = reservationRepository;
        this.authentication = authentication;
    }

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation createdReservation = reservationRepository.save(mapReservationDtoToReservation(reservationDto));
        return EntityToDtoMapper.mapReservationToReservationDto(createdReservation);
    }

    @Override
    public PaginationResponse<ReservationDto> getAllReservation(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Reservation> pagedResult = reservationRepository.findAll(paging);

        PaginationResponse<ReservationDto> paginationResponse = new PaginationResponse<>();
        if (pagedResult.hasContent()) {
            List<ReservationDto> reservationDtos = EntityToDtoMapper.mapReservationListToReservationDtoList(pagedResult.getContent());
            paginationResponse.setData(reservationDtos);
            paginationResponse.setTotalCount(pagedResult.getTotalElements());
        }
        return paginationResponse;
    }

    private Reservation mapReservationDtoToReservation(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setReservationFrom(reservationDto.getReservationFrom());
        reservation.setReservationTo(reservationDto.getReservationTo());
        reservation.setAmount(reservationDto.getAmount());
        reservation.setUser(authentication.getCurrentUser());
        return reservation;
    }
}
