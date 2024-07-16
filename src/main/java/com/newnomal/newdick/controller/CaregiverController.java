package com.newnomal.newdick.controller;


import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.service.CaregiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/caregiver")
public class CaregiverController {
    private final CaregiverService caregiverService;

    @GetMapping("/caregiverId/{caregiverId}")
    public ResponseEntity<RestResult<Object>> getCaregiverById(@PathVariable Long caregiverId) {
        return caregiverService.getCaregiverById(caregiverId);
    }


    @DeleteMapping("/caregiverId/{caregiverId}")
    public ResponseEntity<RestResult<Object>> deleteCaregiver(@PathVariable Long caregiverId) {
        return caregiverService.deleteCaregiver(caregiverId);
    }

}
