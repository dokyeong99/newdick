package com.newnomal.newdick.domain.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CaregiverLoginRequest {
    private String email;
    private String password;
}
