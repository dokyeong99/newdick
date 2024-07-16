package com.newnomal.newdick.controller;


import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.request.CaregiverLoginRequest;
import com.newnomal.newdick.domain.request.CaregiverSignUpRequest;
import com.newnomal.newdick.domain.request.UserLoginRequest;
import com.newnomal.newdick.domain.request.UserSignUpRequest;
import com.newnomal.newdick.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/patient/signup")
    public ResponseEntity<RestResult<Object>> patientSignUp(
            @RequestBody UserSignUpRequest userSignUpRequest) {
        return authService.patientSignUp(userSignUpRequest);
    }

    @PostMapping("/caregiver/signup")
    public ResponseEntity<RestResult<Object>> caregiverSignUp(
            @RequestBody CaregiverSignUpRequest caregiverSignUpRequest) {
        return authService.caregiverSignUp(caregiverSignUpRequest);
    }

    @PostMapping("/patient/login")
    public ResponseEntity<RestResult<Object>> patientLogIn(
            @RequestBody UserLoginRequest userLoginRequest
    ) {
        return authService.patientLogIn(userLoginRequest);
    }
    @PostMapping("/caregiver/login")
    public ResponseEntity<RestResult<Object>> caregiverLogIn(
            @RequestBody CaregiverLoginRequest caregiverLoginRequest
    ) {
        return authService.caregiverLogIn(caregiverLoginRequest);
    }

}
