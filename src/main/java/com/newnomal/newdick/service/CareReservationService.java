package com.newnomal.newdick.service;

import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.CareReservation;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.User;
import com.newnomal.newdick.domain.request.CareReservationRequest;
import com.newnomal.newdick.domain.request.CaregiverReservationRequest;
import com.newnomal.newdick.domain.response.CareReservationResponse;
import com.newnomal.newdick.domain.response.CaregiverResponse;
import com.newnomal.newdick.repositroy.CareReservationRepository;
import com.newnomal.newdick.repositroy.CaregiverRepository;
import com.newnomal.newdick.repositroy.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CareReservationService {
    private final CareReservationRepository careReservationRepository;
    private final UserRepository userRepository;
    private final CaregiverService caregiverService;

    public ResponseEntity<RestResult<Object>> createReservation(CareReservationRequest careReservationRequest) {
        Optional<User> user = userRepository.findById(careReservationRequest.getUserId());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("USER_CONFLICT","존재하지 않는 유저입니다.")));
        }

        CareReservation careReservation = new CareReservation(careReservationRequest);
        // Set reservation fields from request
        careReservation.setUser(user.get());
        careReservation = careReservationRepository.save(careReservation);

        return ResponseEntity.ok(new RestResult<>("SUCCESS", new CareReservationResponse(careReservation)));
    }

    public ResponseEntity<RestResult<Object>> getReservationById(Long reservationId) {
        Optional<CareReservation> reservation = careReservationRepository.findById(reservationId);
        if (reservation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("RERVATION_CONFLICT","존재하지 않는 예약입니다.")));
        }
        return ResponseEntity.ok(new RestResult<>("SUCCESS", new CareReservationResponse(reservation.get())));
    }


    public ResponseEntity<RestResult<Object>> updateReservation(Long reservationId, CareReservationRequest careReservationRequest) {
        Optional<CareReservation> optionalCareReservation = careReservationRepository.findById(reservationId);
        if (optionalCareReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("RESERVATION_CONFLICT","존재하지 않는 예약입니다.")));
        }
        // Update reservation fields from request
        CareReservation careReservation = careReservationRepository.save(new CareReservation(careReservationRequest));
        return ResponseEntity.ok(new RestResult<>("SUCCESS", new CareReservationResponse(careReservation)));
    }

    public ResponseEntity<RestResult<Object>> deleteReservation(Long reservationId) {
        Optional<CareReservation> optionalCareReservation = careReservationRepository.findById(reservationId);
        if (optionalCareReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("RESERVATION_CONFLICT","존재하지 않는 예약입니다.")));
        }
        careReservationRepository.deleteById(reservationId);
        return ResponseEntity.ok(new RestResult<>("SUCCESS", "Reservation deleted successfully"));
    }

    public ResponseEntity<RestResult<Page<CareReservationResponse>>> getReservationsByUserId(Long userId, Pageable pageable) {
        Page<CareReservation> reservations = careReservationRepository.findByUserId(userId, pageable);
        Page<CareReservationResponse> responses = reservations.map(CareReservationResponse::new);
        return ResponseEntity.ok(new RestResult<>("SUCCESS", responses));
    }

    public ResponseEntity<RestResult<Page<CareReservationResponse>>> getReservationsByCaregiverId(Long caregiverId, Pageable pageable) {
        Page<CareReservation> reservations = careReservationRepository.findByCaregiverId(caregiverId, pageable);
        Page<CareReservationResponse> responses = reservations.map(CareReservationResponse::new);
        return ResponseEntity.ok(new RestResult<>("SUCCESS", responses));
    }

    //간병인 예약 요청 -> 특정 간병인 요청 상태 state 1
    public ResponseEntity<RestResult<Object>> reservationCaregiverRequest( CaregiverReservationRequest request) {
        Optional<CareReservation> optionalCareReservation = careReservationRepository.findById(request.getReservationId());
        ResponseEntity<RestResult<Object>> caregiverResponseEntity = caregiverService.getCaregiverById(request.getCaregiverId());
        if(optionalCareReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "예약이 존재하지 않습니다")));
        }
        if (caregiverResponseEntity.getStatusCode()==HttpStatus.CONFLICT){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "해당 간병인이 존재하지 않습니다")));
        };
        CareReservation reservation = optionalCareReservation.get();
        reservation.setCaregiver(Caregiver.builder().id(request.getCaregiverId()).build());
        reservation.setState(1);
        return ResponseEntity.ok(new RestResult<>("OK","간병인 요청 완료"));
    }

    //간병인 예약 등록 -> 간병인이 허가하면 state 2
    public ResponseEntity<RestResult<Object>> reservationAccept( CaregiverReservationRequest request) {
        Optional<CareReservation> optionalCareReservation = careReservationRepository.findById(request.getReservationId());
        ResponseEntity<RestResult<Object>> caregiverResponseEntity = caregiverService.getCaregiverById(request.getCaregiverId());
        if(optionalCareReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "예약이 존재하지 않습니다")));
        }
        if (caregiverResponseEntity.getStatusCode()==HttpStatus.CONFLICT){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "해당 간병인이 존재하지 않습니다")));
        };
        CareReservation reservation = optionalCareReservation.get();
        reservation.setCaregiver(Caregiver.builder().id(request.getCaregiverId()).build());
        reservation.setState(2);
        return ResponseEntity.ok(new RestResult<>("OK","간병인 허가 완료"));
    }

    //간병인 예약 거절 -> 간병인이 거절하면 state 3
    public ResponseEntity<RestResult<Object>> reservationDeny( CaregiverReservationRequest request) {
        Optional<CareReservation> optionalCareReservation = careReservationRepository.findById(request.getReservationId());
        ResponseEntity<RestResult<Object>> caregiverResponseEntity = caregiverService.getCaregiverById(request.getCaregiverId());
        if(optionalCareReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "예약이 존재하지 않습니다")));
        }
        if (caregiverResponseEntity.getStatusCode()==HttpStatus.CONFLICT){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "해당 간병인이 존재하지 않습니다")));
        };
        CareReservation reservation = optionalCareReservation.get();
        reservation.setCaregiver(Caregiver.builder().id(request.getCaregiverId()).build());
        reservation.setState(3);
        return ResponseEntity.ok(new RestResult<>("OK","간병인 요청 거부 완료"));

    }

    //예약 공개요청 상태 -> 특정 간병인 아닌 전체 대상 간병인 요청 상태 -> state 4
    public ResponseEntity<RestResult<Object>> reservationToAll( CaregiverReservationRequest request) {
        Optional<CareReservation> optionalCareReservation = careReservationRepository.findById(request.getReservationId());
        ResponseEntity<RestResult<Object>> caregiverResponseEntity = caregiverService.getCaregiverById(request.getCaregiverId());
        if(optionalCareReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "예약이 존재하지 않습니다")));
        }
        if (caregiverResponseEntity.getStatusCode()==HttpStatus.CONFLICT){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "해당 간병인이 존재하지 않습니다")));
        };
        CareReservation reservation = optionalCareReservation.get();
        reservation.setCaregiver(Caregiver.builder().id(request.getCaregiverId()).build());
        reservation.setState(4);
        return ResponseEntity.ok(new RestResult<>("OK","전체 간병인 추천 설정 완료"));
    }
}