package com.newnomal.newdick.domain.entity;


import com.newnomal.newdick.domain.request.CareReservationRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter @Setter
@Table(name = "CareReservationTable")
public class CareReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer dailyStartTime;
    private Integer dailyEndTime;
    private String reservationReason;
    private String reservationRequest;
    private String reservationStatus;
    private String reservationLocation;
    @ManyToOne(fetch = FetchType.LAZY)
    private Caregiver caregiver;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String patientName;
    private String patientGender;
    private LocalDate patientBirthDate;
    private String patientHeight;
    private String patientWeight;

    public CareReservation(CareReservationRequest careReservationRequest){
        this.startDate = careReservationRequest.getStartDate();
        this.endDate = careReservationRequest.getEndDate();
        this.dailyStartTime = careReservationRequest.getDailyStartTime();
        this.dailyEndTime = careReservationRequest.getDailyEndTime();
        this.reservationReason = careReservationRequest.getReservationReason();
        this.reservationRequest = careReservationRequest.getReservationRequest();
        this.patientName = careReservationRequest.getPatientName();
        this.patientGender = careReservationRequest.getPatientGender();
        this.patientBirthDate = careReservationRequest.getPatientBirthDate();
        this.patientHeight = careReservationRequest.getPatientHeight();
        this.patientWeight = careReservationRequest.getPatientWeight();
    }

}
