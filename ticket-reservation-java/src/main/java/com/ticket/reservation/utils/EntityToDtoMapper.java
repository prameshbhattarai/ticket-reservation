package com.ticket.reservation.utils;

import com.ticket.reservation.dto.ReservationDto;
import com.ticket.reservation.dto.ReservationPaymentDto;
import com.ticket.reservation.dto.ReservationUserDto;
import com.ticket.reservation.entities.Reservation;
import com.ticket.reservation.entities.ReservationPayment;
import com.ticket.reservation.entities.ReservationUser;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pramesh-bhattarai
 */
public class EntityToDtoMapper {

    private EntityToDtoMapper() {}

    public static List<ReservationDto> mapReservationListToReservationDtoList(List<Reservation> reservations) {
        return reservations.stream().map(EntityToDtoMapper::mapReservationToReservationDto).collect(Collectors.toList());
    }

    public static ReservationUserDto mapReservationUserToReservationUserDto(ReservationUser reservationUser) {
        ReservationUserDto reservationUserDto = new ReservationUserDto();
        reservationUserDto.setUsername(reservationUser.getUsername());
        reservationUserDto.setId(reservationUser.getId());
        return reservationUserDto;
    }

    public static ReservationDto mapReservationToReservationDto(Reservation reservation) {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setAmount(reservation.getAmount());
        reservationDto.setReservationFrom(reservation.getReservationFrom());
        reservationDto.setReservationTo(reservation.getReservationTo());
        reservationDto.setUser(mapReservationUserToReservationUserDto(reservation.getUser()));
        return reservationDto;
    }

    public static List<ReservationPaymentDto> mapReservationPaymentListToReservationPaymentDtoList(List<ReservationPayment> reservationPayments) {
        return reservationPayments.stream().map(EntityToDtoMapper::mapReservationPaymentToReservationPaymentDto).collect(Collectors.toList());
    }

    public static ReservationPaymentDto mapReservationPaymentToReservationPaymentDto(ReservationPayment reservationPayment) {
        ReservationPaymentDto reservationPaymentDto = new ReservationPaymentDto();
        reservationPaymentDto.setId(reservationPayment.getId());
        reservationPaymentDto.setPaymentAmount(reservationPayment.getPaymentAmount());
        reservationPaymentDto.setReservation(mapReservationToReservationDto(reservationPayment.getReservation()));
        return reservationPaymentDto;
    }

}
