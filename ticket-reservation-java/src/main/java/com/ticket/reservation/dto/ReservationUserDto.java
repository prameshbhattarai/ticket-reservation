package com.ticket.reservation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

/**
 * @author pramesh-bhattarai
 */
@Getter
@Setter
@ToString()
@EqualsAndHashCode()
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationUserDto implements Serializable {

    private long id;
    private String username;
}
