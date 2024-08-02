package com.newnomal.newdick.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CaregiverSignUpRequest {
    private String email;
    private String password;
    private String phone;
    private String name;
    private String birthDate;
    private String gender;
    private String certification;
    private List<WorkHistorySignUpRequest> workHistory;
    private String licenseType;
    private String careerDescription;//elastic search랑 연동되는 파라미터




}
