package com.newnomal.newdick.controller;


import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.request.*;
import com.newnomal.newdick.domain.response.CareReservationResponse;
import com.newnomal.newdick.service.CareReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/care-reservation")
@CrossOrigin("*")
public class CareReservationController {
    private final CareReservationService careReservationService;

    //간병 예약 등록 -> 간병인 요청 전 상태 state 0
    @PostMapping("/careReservation")
    public ResponseEntity<RestResult<Object>> createReservation(@RequestBody CareReservationRequest request) {
        return careReservationService.createReservation(request);
    }

    //간병인 예약 요청 -> 특정 간병인 요청 상태 state 1
    @PostMapping("/careRequest")
    public ResponseEntity<RestResult<Object>> reservationCaregiverRequest(@RequestBody CaregiverReservationRequest request) {
        return null;
    }

    //간병인 예약 등록 -> 간병인이 허가하면 state 2
    @PostMapping("/careAccept")
    public ResponseEntity<RestResult<Object>> reservationAccept(@RequestBody CaregiverReservationRequest request) {
        return null;
    }

    //간병인 예약 거절 -> 간병인이 거절하면 state 3
    @PostMapping("/careDeny")
    public ResponseEntity<RestResult<Object>> reservationDeny(@RequestBody CaregiverReservationRequest request) {
        return null;
    }

    //예약 공개요청 상태 -> 특정 간병인 아닌 전체 대상 간병인 요청 상태 -> state 4
    @PostMapping("/careRequestChange")
    public ResponseEntity<RestResult<Object>> reservationToAll(@RequestBody CaregiverReservationRequest request) {
        return null;
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<RestResult<Object>> getReservationById(@PathVariable Long reservationId) {
        return careReservationService.getReservationById(reservationId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResult<Page<CareReservationResponse>>> getReservationsByUserId(
            @PathVariable Long userId, Pageable pageable) {
        return careReservationService.getReservationsByUserId(userId, pageable);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<RestResult<Page<CareReservationResponse>>> getReservationsByCaregiverId(
            @PathVariable Long caregiverId, Pageable pageable) {
        return careReservationService.getReservationsByCaregiverId(caregiverId, pageable);
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<RestResult<Object>> updateReservation(@PathVariable Long reservationId, @RequestBody CareReservationRequest request) {
        return careReservationService.updateReservation(reservationId, request);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<RestResult<Object>> deleteReservation(@PathVariable Long reservationId) {
        return careReservationService.deleteReservation(reservationId);
    }
}
