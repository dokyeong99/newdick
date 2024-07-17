package com.newnomal.newdick.service;

import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.WorkHistory;
import com.newnomal.newdick.domain.request.WorkHistoryRequest;
import com.newnomal.newdick.domain.response.WorkHistoryResponse;
import com.newnomal.newdick.repositroy.WorkHistoryRepository;
import com.newnomal.newdick.service.CaregiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkHistoryService {
    private final WorkHistoryRepository workHistoryRepository;
    private final CaregiverService caregiverService;

    public ResponseEntity<RestResult<Object>> createWorkHistory(WorkHistoryRequest workHistoryRequest) {
        ResponseEntity<RestResult<Object>> caregiverResponse = caregiverService.getCaregiverById(workHistoryRequest.getCaregiverId());
        if (caregiverResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("Caregiver not found", "해당 간병인을 찾을 수 없습니다")));
        }

        WorkHistory workHistory = new WorkHistory(workHistoryRequest);
        workHistory = workHistoryRepository.save(workHistory);

        return ResponseEntity.ok(new RestResult<>("SUCCESS", new WorkHistoryResponse(workHistory)));
    }

    public ResponseEntity<RestResult<Object>> getWorkHistoryById(Long workHistoryId) {
        Optional<WorkHistory> workHistoryOptional = workHistoryRepository.findById(workHistoryId);
        if (workHistoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("No Data", "해당 근무 이력을 찾을 수 없습니다")));
        }
        return ResponseEntity.ok(new RestResult<>("SUCCESS", new WorkHistoryResponse(workHistoryOptional.get())));
    }

    public ResponseEntity<RestResult<Object>> getWorkHistoriesByCaregiverId(Long caregiverId) {
        List<WorkHistory> workHistories = workHistoryRepository.findByCaregiverId(caregiverId);
        List<WorkHistoryResponse> responses = workHistories.stream()
                .map(WorkHistoryResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new RestResult<>("SUCCESS", responses));
    }

    public ResponseEntity<RestResult<Object>> updateWorkHistory(Long workHistoryId, WorkHistoryRequest request) {
        Optional<WorkHistory> workHistoryOptional = workHistoryRepository.findById(workHistoryId);
        if (workHistoryOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("No Data", "해당 근무 이력을 찾을 수 없습니다")));
        }
        WorkHistory workHistory = workHistoryOptional.get();
        workHistory.setWorkHistory(request.getWorkHistory());
        workHistory.setWorkHistoryPeriod(request.getWorkHistoryPeriod());
        workHistory = workHistoryRepository.save(workHistory);
        return ResponseEntity.ok(new RestResult<>("SUCCESS", new WorkHistoryResponse(workHistory)));
    }

    public ResponseEntity<RestResult<Object>> deleteWorkHistory(Long workHistoryId) {
        try {
            workHistoryRepository.deleteById(workHistoryId);
            return ResponseEntity.ok(new RestResult<>("SUCCESS", "근무 이력이 성공적으로 삭제되었습니다"));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("Delete Failed", exception.getMessage())));
        }
    }
}