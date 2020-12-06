package com.ticket.reservation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.reservation.exception.ReservationException;

import java.io.IOException;

/**
 * @author pramesh-bhattarai
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils(){}

    public static String writeObjectAsString(Object object) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (IOException e) {
            throw new ReservationException(e);
        }
    }
}
