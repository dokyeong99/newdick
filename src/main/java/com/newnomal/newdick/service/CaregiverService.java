package com.newnomal.newdick.service;

import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.response.CaregiverResponse;
import com.newnomal.newdick.repositroy.CaregiverRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CaregiverService {
    private final CaregiverRepository caregiverRepository;

    public ResponseEntity<RestResult<Object>> getAllCaregivers(Pageable pageable) {
        Page<Caregiver> caregiverPage = caregiverRepository.findAll(pageable);

        List<CaregiverResponse> caregiverResponses = caregiverPage.getContent().stream()
                .map(caregiver -> new CaregiverResponse(caregiver, "생성자 분별용 null Text"))
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("caregivers", caregiverResponses);
        response.put("currentPage", caregiverPage.getNumber());
        response.put("totalItems", caregiverPage.getTotalElements());
        response.put("totalPages", caregiverPage.getTotalPages());

        return ResponseEntity.ok(new RestResult<>("SUCCESS", response));
    }


    public ResponseEntity<RestResult<Object>> getCaregiverById(Long caregiverId) {
        Optional<Caregiver> caregiverOptional = caregiverRepository.findById(caregiverId);
        if (caregiverOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "해당 아이디로 정보를 찾을 수 없습니다")));
        }
        CaregiverResponse caregiverResponse = new CaregiverResponse(caregiverOptional.get(),"생성자 분별용 null Text");
        return ResponseEntity.ok(new RestResult<>("SUCCESS", caregiverResponse));
    }

    public ResponseEntity<RestResult<Object>> deleteCaregiver(@PathVariable Long caregiverId) {
        try {
            caregiverRepository.deleteById(caregiverId);
            return ResponseEntity.ok(new RestResult<>("SUCCESS", "Delete Clear"));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("Delete Failed", exception.getMessage())));
        }
    }

}