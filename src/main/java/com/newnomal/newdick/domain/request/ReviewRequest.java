package com.newnomal.newdick.domain.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewRequest {
    private Long userId;
    private Long caregiverId;
    private Integer rating;
    private String comment;
}
