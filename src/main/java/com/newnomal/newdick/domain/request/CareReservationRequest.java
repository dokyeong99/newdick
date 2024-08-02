package com.newnomal.newdick.domain.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareReservationRequest {
    private String patientName;
    private LocalDate patientBirthDate;
    private String patientHeight;
    private String patientWeight;
    private String patientGender;

    private String diseaseName = "";
    private String reservationLocation;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime dailyStartTime;
    private LocalTime dailyEndTime;

    private String reservationReason;
    private String UnAcceptedBehavior = "";
    private String RecentDiseaseData = "";

    //new field
    private Long userId;
}
