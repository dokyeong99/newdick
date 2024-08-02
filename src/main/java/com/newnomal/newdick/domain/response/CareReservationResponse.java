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
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CareReservationResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime dailyStartTime;
    private LocalTime dailyEndTime;
    private String reservationReason;
    private String reservationLocation;
    private String diseaseName;
    private CaregiverResponse caregiverResponse;
    private UserResponse userResponse;
    private String patientName;
    private String patientGender;
    private LocalDate patientBirthDate;
    private String patientHeight;
    private String patientWeight;
    private Integer state;
    private String UnAcceptedBehavior;
    private String RecentDiseaseData;

    public CareReservationResponse(CareReservation careReservation) {
        this.id = careReservation.getId();
        this.startDate = careReservation.getStartDate();
        this.endDate = careReservation.getEndDate();
        this.dailyStartTime = careReservation.getDailyStartTime();
        this.dailyEndTime = careReservation.getDailyEndTime();
        this.reservationReason = careReservation.getReservationReason();
        this.reservationLocation = careReservation.getReservationLocation();
        this.diseaseName = careReservation.getDiseaseName();
        this.caregiverResponse = new CaregiverResponse(careReservation.getCaregiver());
        this.userResponse = new UserResponse(careReservation.getUser());
        this.patientName = careReservation.getPatientName();
        this.patientGender = careReservation.getPatientGender();
        this.patientBirthDate = careReservation.getPatientBirthDate();
        this.patientHeight = careReservation.getPatientHeight();
        this.patientWeight = careReservation.getPatientWeight();
        this.state = careReservation.getState();
        this.UnAcceptedBehavior = careReservation.getUnAcceptedBehavior();

    }

}
