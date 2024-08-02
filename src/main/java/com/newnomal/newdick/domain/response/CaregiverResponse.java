package com.newnomal.newdick.domain.response;


import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.WorkHistory;
import com.newnomal.newdick.domain.entity.Certification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CaregiverResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String city;
    private String careerDescription;//elastic search랑 연동되는 파라미터
    private List<Certification> certifications;//인증 이후에 업데이트
    private List<CareReservationResponse> careReservations;
    private List<WorkHistory> caregiverWorkHistories;
    private Double averageRating;

    public CaregiverResponse(Caregiver caregiver, String noData) {
        this.id = caregiver.getId();
        this.name = caregiver.getName();
        this.phone = caregiver.getPhone();
        this.gender = caregiver.getGender();
        this.birthDate = caregiver.getBirthDate();
        this.address = caregiver.getAddress();
        this.city = caregiver.getCity();
        this.careerDescription = caregiver.getCareerDescription();
        this.certifications = caregiver.getCertifications();
        this.careReservations = caregiver.getCareReservations().stream().map(CareReservationResponse::new).toList();
        this.averageRating = caregiver.getAverageRating();
        this.caregiverWorkHistories = caregiver.getWorkHistories();
    }

    public CaregiverResponse(Caregiver entity) {
        if (entity != null) {this.id = entity.getId();
                            this.name = entity.getName();
        }
    }

}
