package com.newnomal.newdick.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSignUpRequest {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private String birthDate;
    private String address;
    private String city;
}
