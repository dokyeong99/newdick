package com.newnomal.newdick.controller;


import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.service.CaregiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/caregiver")
@CrossOrigin("*")
public class CaregiverController {
    private final CaregiverService caregiverService;

    @GetMapping
    public ResponseEntity<RestResult<Object>> getAllCaregivers(
            Pageable pageable) {
        return caregiverService.getAllCaregivers(pageable);
    }

    @GetMapping("/caregiverId/{caregiverId}")
    public ResponseEntity<RestResult<Object>> getCaregiverById(@PathVariable Long caregiverId) {
        return caregiverService.getCaregiverById(caregiverId);
    }

    @DeleteMapping("/caregiverId/{caregiverId}")
    public ResponseEntity<RestResult<Object>> deleteCaregiver(@PathVariable Long caregiverId) {
        return caregiverService.deleteCaregiver(caregiverId);
    }

    @PostMapping("/byIds")
    public ResponseEntity<RestResult<Object>> getCaregiversByIds(@RequestBody List<Long> caregiverIds) {
        return caregiverService.getCaregiversByIds(caregiverIds);
    }
}

