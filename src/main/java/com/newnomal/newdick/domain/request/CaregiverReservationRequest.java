package com.newnomal.newdick.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaregiverReservationRequest {
    private Long caregiverId;
    private Long reservationId;

}
