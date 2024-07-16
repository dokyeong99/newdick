package com.newnomal.newdick.service;

import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.User;
import com.newnomal.newdick.domain.request.CaregiverLoginRequest;
import com.newnomal.newdick.domain.request.CaregiverSignUpRequest;
import com.newnomal.newdick.domain.request.UserLoginRequest;
import com.newnomal.newdick.domain.request.UserSignUpRequest;
import com.newnomal.newdick.repositroy.CaregiverRepository;
import com.newnomal.newdick.repositroy.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final CaregiverRepository caregiverRepository;
    private final UserRepository userRepository;
    private final OpenAIApiService openAIApiService;

    //1. 이메일 중복 체크 ->에러 시에 에러 반환
    //2. 데이터 삽입
    public ResponseEntity<RestResult<Object>> patientSignUp(
            UserSignUpRequest userSignUpRequest) {
        Optional<User> byEmail = userRepository.findByEmail(userSignUpRequest.getEmail());
        if(byEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("EMAIL_CONFLICT","이미 존재하는 이메일 입니다.")));
        }
        User user = userRepository.save(new User(userSignUpRequest));
        return ResponseEntity.ok(new RestResult<>("success", user.getId()));
    }

    //1. 이메일 중복 체크 ->에러 시에 에러 반환
    //2. 데이터 삽입
    public ResponseEntity<RestResult<Object>> caregiverSignUp(
            CaregiverSignUpRequest caregiverSignUpRequest) {
        Optional<Caregiver> byEmail = caregiverRepository.findByEmail(caregiverSignUpRequest.getEmail());
        if(byEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("EMAIL_CONFLICT","이미 존재하는 이메일 입니다.")));
        }
        //워드 임배딩 openapi로 가져오기
        //
        List<Double> embedingVector = openAIApiService.generateCareerDescriptionVector(caregiverSignUpRequest.getCareerDescription());
        Caregiver beforeSave = new Caregiver(caregiverSignUpRequest);
        beforeSave.setCareerDescriptionVector(embedingVector);
        Caregiver caregiver = caregiverRepository.save(beforeSave);
        return ResponseEntity.ok(new RestResult<>("success",caregiver.getId()));
    }

    //1. 이메일, 비밀번호 체크 ->에러 시에 에러 반환
    //2. patient아이디 반환
    public ResponseEntity<RestResult<Object>> patientLogIn(
            UserLoginRequest userLoginRequest) {
        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        if(byEmailAndPassword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("Login_Error","비밀번호 혹인 아이디가 잘못 되었습니다")));
        }
        return ResponseEntity.ok(new RestResult<>("success",byEmailAndPassword.get().getId()));
    }

    //1. 이메일, 비밀번호 체크 ->에러 시에 에러 반환
    //2. patient아이디 반환
    public ResponseEntity<RestResult<Object>> caregiverLogIn(
            CaregiverLoginRequest caregiverLoginRequest) {
        Optional<Caregiver> caregiver = caregiverRepository.findByEmailAndPassword(caregiverLoginRequest.getEmail(), caregiverLoginRequest.getPassword());
        if(caregiver.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("Conflict",new RestError("Login_Error","비밀번호 혹인 아이디가 잘못 되었습니다")));
        }
        return ResponseEntity.ok(new RestResult<>("success",caregiver.get().getId()));
    }

}

