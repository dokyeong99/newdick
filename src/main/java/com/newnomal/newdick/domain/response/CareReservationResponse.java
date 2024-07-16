package com.newnomal.newdick.domain.response;

import com.newnomal.newdick.domain.entity.CareReservation;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CareReservationResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer dailyStartTime;
    private Integer dailyEndTime;
    private String reservationReason;
    private String reservationRequest;
    private String reservationStatus;
    private String reservationLocation;
    private CaregiverResponse caregiverResponse;
    private UserResponse userResponse;
    private String patientName;
    private String patientGender;
    private LocalDate patientBirthDate;
    private String patientHeight;
    private String patientWeight;

    public CareReservationResponse(CareReservation careReservation) {
        this.id = careReservation.getId();
        this.startDate = careReservation.getStartDate();
        this.endDate = careReservation.getEndDate();
        this.dailyStartTime = careReservation.getDailyStartTime();
        this.dailyEndTime = careReservation.getDailyEndTime();
        this.reservationReason = careReservation.getReservationReason();
        this.reservationRequest = careReservation.getReservationRequest();
        this.reservationStatus = careReservation.getReservationStatus();
        this.reservationLocation = careReservation.getReservationLocation();
        this.caregiverResponse = new CaregiverResponse(careReservation.getCaregiver());
        this.userResponse = new UserResponse(careReservation.getUser());
        this.patientName = careReservation.getPatientName();
        this.patientGender = careReservation.getPatientGender();
        this.patientBirthDate = careReservation.getPatientBirthDate();
        this.patientHeight = careReservation.getPatientHeight();
        this.patientWeight = careReservation.getPatientWeight();


    }

}
