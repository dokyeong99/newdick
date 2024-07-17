package com.newnomal.newdick.controller;

import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.request.ReviewRequest;
import com.newnomal.newdick.domain.response.ReviewResponse;
import com.newnomal.newdick.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<RestResult<Object>> createReview(@RequestBody ReviewRequest request) {
        return reviewService.createReview(request);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<RestResult<Object>> getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @GetMapping("/caregiver/{caregiverId}")
    public ResponseEntity<RestResult<Page<ReviewResponse>>> getReviewsByCaregiverId(
            @PathVariable Long caregiverId, Pageable pageable) {
        return reviewService.getReviewsByCaregiverId(caregiverId, pageable);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<RestResult<Object>> updateReview(@PathVariable Long reviewId, @RequestBody ReviewRequest request) {
        return reviewService.updateReview(reviewId, request);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<RestResult<Object>> deleteReview(@PathVariable Long reviewId) {
        return reviewService.deleteReview(reviewId);
    }
}
