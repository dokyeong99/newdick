package com.newnomal.newdick.controller;

import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.request.WorkHistoryRequest;
import com.newnomal.newdick.service.WorkHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/work-history")
public class WorkHistoryController {
    private final WorkHistoryService workHistoryService;

    @PostMapping
    public ResponseEntity<RestResult<Object>> createWorkHistory(@RequestBody WorkHistoryRequest request) {
        return workHistoryService.createWorkHistory(request);
    }

    @GetMapping("/{workHistoryId}")
    public ResponseEntity<RestResult<Object>> getWorkHistoryById(@PathVariable Long workHistoryId) {
        return workHistoryService.getWorkHistoryById(workHistoryId);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<RestResult<Object>> getWorkHistoriesByCaregiverId(@PathVariable Long caregiverId) {
        return workHistoryService.getWorkHistoriesByCaregiverId(caregiverId);
    }

    @PutMapping("/{workHistoryId}")
    public ResponseEntity<RestResult<Object>> updateWorkHistory(@PathVariable Long workHistoryId, @RequestBody WorkHistoryRequest request) {
        return workHistoryService.updateWorkHistory(workHistoryId, request);
    }

    @DeleteMapping("/{workHistoryId}")
    public ResponseEntity<RestResult<Object>> deleteWorkHistory(@PathVariable Long workHistoryId) {
        return workHistoryService.deleteWorkHistory(workHistoryId);
    }
}