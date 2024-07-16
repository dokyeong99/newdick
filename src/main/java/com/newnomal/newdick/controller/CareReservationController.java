package com.newnomal.newdick.controller;


import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.request.*;
import com.newnomal.newdick.service.CareReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/care-reservation")
public class CareReservationController {
    private final CareReservationService careReservationService;

    @PostMapping
    public ResponseEntity<RestResult<Object>> createReservation(@RequestBody CareReservationRequest request) {
        return careReservationService.createReservation(request);
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<RestResult<Object>> getReservationById(@PathVariable Long reservationId) {
        return careReservationService.getReservationById(reservationId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResult<Object>> getReservationsByUserId(@PathVariable Long userId) {
        return careReservationService.getReservationsByUserId(userId);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<RestResult<Object>> getReservationsByCaregiverId(@PathVariable Long caregiverId) {
        return careReservationService.getReservationsByCaregiverId(caregiverId);
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
