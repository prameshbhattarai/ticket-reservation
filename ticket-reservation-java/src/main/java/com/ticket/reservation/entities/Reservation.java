package com.ticket.reservation.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author pramesh-bhattarai
 */
@Entity
@Table(name = "reservation")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "reservation_from")
    private String reservationTo;

    @Column(name = "reservation_to")
    private String reservationFrom;

    @Column
    private double amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reserved_by", referencedColumnName = "id")
    private ReservationUser user;

}
