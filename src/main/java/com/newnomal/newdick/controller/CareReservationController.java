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
    @PostMapping("/careReservationInput")
    public ResponseEntity<RestResult<Object>> createReservation(@RequestBody CareReservationRequest request) {
        return careReservationService.createReservation(request);
    }

    //간병인 예약 요청 -> 특정 간병인 요청 상태 state 1
    @PostMapping("/careReservationRequest")
    public ResponseEntity<RestResult<Object>> reservationCaregiverRequest(@RequestBody CaregiverReservationRequest request) {
        return careReservationService.reservationCaregiverRequest(request);
    }

    //간병인 예약 등록 -> 간병인이 허가하면 state 2
    @PostMapping("/careReservationAccept")
    public ResponseEntity<RestResult<Object>> reservationAccept(@RequestBody CaregiverReservationRequest request) {
        return careReservationService.reservationAccept(request);
    }

    //간병인 예약 거절 -> 간병인이 거절하면 state 3
    @PostMapping("/careReservationDeny")
    public ResponseEntity<RestResult<Object>> reservationDeny(@RequestBody CaregiverReservationRequest request) {
        return careReservationService.reservationDeny(request);
    }

    //예약 공개요청 상태 -> 특정 간병인 아닌 전체 대상 간병인 요청 상태 -> state 4
    @PostMapping("/careRequestAll")
    public ResponseEntity<RestResult<Object>> reservationToAll(@RequestBody CaregiverReservationRequest request) {
        return careReservationService.reservationToAll(request);
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

    @GetMapping("/caregiver/requested/{caregiverId}")
    public ResponseEntity<RestResult<Page<CareReservationResponse>>> getRequestedReservationsByCaregiverId(
            @PathVariable Long caregiverId, Pageable pageable) {
        return careReservationService.getRequestedReservationsByCaregiverId(caregiverId, pageable);
    }

    @GetMapping("/caregiver/allrequested/{caregiverId}")
    public ResponseEntity<RestResult<Page<CareReservationResponse>>> getAllRequestedReservationsByCaregiverId(
            @PathVariable Long caregiverId, Pageable pageable) {
        return careReservationService.getAllRequestedReservationsByCaregiverId(caregiverId, pageable);
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
