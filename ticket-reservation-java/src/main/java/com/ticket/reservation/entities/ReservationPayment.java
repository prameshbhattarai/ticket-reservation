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
@Table(name = "reservation_payment")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReservationPayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "payment_amount")
    private double paymentAmount;

    @OneToOne
    @JoinColumn(name = "reservation", referencedColumnName = "id")
    private Reservation reservation;

}
