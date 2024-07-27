package com.newnomal.newdick.domain.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareReservationRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer dailyStartTime;
    private Integer dailyEndTime;
    private String reservationReason;
    private String reservationRequest;
    private String reservationLocation;
    private Long userId;
    private String patientName;
    private String patientGender;
    private LocalDate patientBirthDate;
    private String patientHeight;
    private String patientWeight;

    //new field
    private String diseaseName = "";
    private String UnAcceptedBehavior = "";
    private String RecentDiseaseData = "";
}
