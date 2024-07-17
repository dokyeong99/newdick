package com.newnomal.newdick.service;

import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.CareReservation;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.User;
import com.newnomal.newdick.domain.request.CareReservationRequest;
import com.newnomal.newdick.domain.response.CareReservationResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CareReservationService {
    private final CareReservationRepository careReservationRepository;
    private final UserRepository userRepository;
    private final CaregiverRepository caregiverRepository;

    public ResponseEntity<RestResult<Object>> createReservation(CareReservationRequest careReservationRequest) {
        Optional<User> user = userRepository.findById(careReservationRequest.getUserId());
        Optional<Caregiver> caregiver = caregiverRepository.findById(careReservationRequest.getCaregiverId());
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("USER_CONFLICT","존재하지 않는 유저입니다.")));
        }
        if (caregiver.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("USER_CONFLICT","존재하지 않는 간병인입니다.")));
        }

        CareReservation careReservation = new CareReservation(careReservationRequest);
        // Set reservation fields from request
        careReservation.setUser(user.get());
        careReservation.setCaregiver(caregiver.get());
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
}