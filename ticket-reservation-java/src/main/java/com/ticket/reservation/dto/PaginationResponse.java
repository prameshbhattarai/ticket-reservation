package com.ticket.reservation.dto;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pramesh-bhattarai
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public final class PaginationResponse<ELEMENT> implements Serializable {

    private List<ELEMENT> data = new ArrayList<>();
    private long totalCount;
}
