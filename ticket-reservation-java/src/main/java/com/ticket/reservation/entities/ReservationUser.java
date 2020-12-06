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
@Table(name = "reservation_user")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReservationUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

}
