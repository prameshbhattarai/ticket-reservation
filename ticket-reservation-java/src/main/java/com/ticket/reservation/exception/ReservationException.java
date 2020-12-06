package com.ticket.reservation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author pramesh-bhattarai
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ReservationException extends RuntimeException {

    public ReservationException() {
        super();
    }

    public ReservationException(Throwable cause) {
        super(cause);
    }

    public ReservationException(String message) {
        super(message);
    }

    public ReservationException(String message, Throwable cause) {
        super(message, cause);
    }

}
