package com.newnomal.newdick.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkHistorySignUpRequest {
    private String workHistory;
    private String workHistoryPeriod;
}
