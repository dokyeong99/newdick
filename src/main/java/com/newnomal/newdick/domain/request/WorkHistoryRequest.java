package com.newnomal.newdick.domain.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkHistoryRequest {
    private Long caregiverId;
    private String workHistory;
    private String workHistoryPeriod;
}
