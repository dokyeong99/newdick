package com.newnomal.newdick.domain.entity;


import com.newnomal.newdick.domain.converter.DoubleListToJsonConverter;
import com.newnomal.newdick.domain.request.CaregiverSignUpRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
@Table(name = "CaregiverTable")
@Setter
public class Caregiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Convert(converter = DoubleListToJsonConverter.class)
    @Column(columnDefinition = "JSON")
    private List<Double> careerDescriptionVector;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Certification> certifications;//인증 이후에 업데이트
    @OneToMany(mappedBy = "caregiver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CareReservation> careReservations;
    @OneToMany(fetch = FetchType.EAGER)
    private List<WorkHistory> WorkHistories;

    public Caregiver(CaregiverSignUpRequest caregiverSignUpRequest){
        this.email = caregiverSignUpRequest.getEmail();
        this.password = caregiverSignUpRequest.getPassword();
        this.name = caregiverSignUpRequest.getName();
        this.phone = caregiverSignUpRequest.getPhone();
        this.gender = caregiverSignUpRequest.getGender();
        this.birthDate = LocalDate.parse(caregiverSignUpRequest.getBirthDate());
        this.address = caregiverSignUpRequest.getAddress();
        this.city = caregiverSignUpRequest.getCity();
        this.careerDescription = caregiverSignUpRequest.getCareerDescription();
    }

}
