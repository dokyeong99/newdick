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
@Table(name = "CareReservationTable",
        indexes = {
                @Index(name = "idx_user", columnList = "user_id"),
                @Index(name = "idx_caregiver", columnList = "caregiver_id")
                })
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
    private Integer state = 0;//간병 등록상태 0 간병인 허가 상태 1 간병인 거절 상태 2

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
