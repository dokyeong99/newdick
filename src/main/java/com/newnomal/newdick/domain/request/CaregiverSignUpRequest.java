package com.newnomal.newdick.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CaregiverSignUpRequest {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private String birthDate;
    private String address;
    private String city;
    private String careerDescription;//elastic search랑 연동되는 파라미터

}
