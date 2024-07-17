package com.newnomal.newdick.service;

import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.Review;
import com.newnomal.newdick.domain.entity.User;
import com.newnomal.newdick.domain.request.ReviewRequest;
import com.newnomal.newdick.domain.response.ReviewResponse;
import com.newnomal.newdick.repositroy.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final CaregiverService caregiverService;

    public ResponseEntity<RestResult<Object>> createReview(ReviewRequest request) {
        ResponseEntity<RestResult<Object>> userResponse = userService.getUserById(request.getUserId());
        if (userResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("User not found", "해당 사용자를 찾을 수 없습니다")));
        }

        ResponseEntity<RestResult<Object>> caregiverResponse = caregiverService.getCaregiverById(request.getCaregiverId());
        if (caregiverResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("Caregiver not found", "해당 간병인을 찾을 수 없습니다")));
        }

        Review review = new Review();
        review.setUser((User) ((RestResult<?>) userResponse.getBody()).getData());
        review.setCaregiver((Caregiver) ((RestResult<?>) caregiverResponse.getBody()).getData());
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setCreatedAt(LocalDateTime.now());

        review = reviewRepository.save(review);

        Caregiver caregiver = review.getCaregiver();
        caregiver.addReview(review.getRating());

        return ResponseEntity.ok(new RestResult<>("SUCCESS", new ReviewResponse(review)));
    }

    public ResponseEntity<RestResult<Object>> getReviewById(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("No Data", "해당 리뷰를 찾을 수 없습니다")));
        }
        return ResponseEntity.ok(new RestResult<>("SUCCESS", new ReviewResponse(reviewOptional.get())));
    }

    public ResponseEntity<RestResult<Page<ReviewResponse>>> getReviewsByCaregiverId(Long caregiverId, Pageable pageable) {
        Page<Review> reviews = reviewRepository.findByCaregiverId(caregiverId, pageable);
        Page<ReviewResponse> responses = reviews.map(ReviewResponse::new);
        return ResponseEntity.ok(new RestResult<>("SUCCESS", responses));
    }

    public ResponseEntity<RestResult<Object>> updateReview(Long reviewId, ReviewRequest request) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("No Data", "해당 리뷰를 찾을 수 없습니다")));
        }
        Review review = reviewOptional.get();
        int oldRating = review.getRating();
        review.setRating(request.getRating());
        review.setComment(request.getComment());

        review = reviewRepository.save(review);

        Caregiver caregiver = review.getCaregiver();
        caregiver.updateRating(oldRating, review.getRating());

        return ResponseEntity.ok(new RestResult<>("SUCCESS", new ReviewResponse(review)));
    }

    public ResponseEntity<RestResult<Object>> deleteReview(Long reviewId) {
        try {
            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
            Caregiver caregiver = review.getCaregiver();
            caregiver.removeReview(review.getRating());
            reviewRepository.deleteById(reviewId);
            return ResponseEntity.ok(new RestResult<>("SUCCESS", "리뷰가 성공적으로 삭제되었습니다"));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT", new RestError("Delete Failed", exception.getMessage())));
        }
    }


}
