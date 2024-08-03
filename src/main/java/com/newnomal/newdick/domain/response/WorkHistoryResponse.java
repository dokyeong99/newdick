package com.newnomal.newdick.domain.response;
import com.newnomal.newdick.domain.entity.Caregiver;
import com.newnomal.newdick.domain.entity.WorkHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkHistoryResponse {
    private Long id;
    private String workHistory;
    private String workHistoryPeriod;

    public WorkHistoryResponse(WorkHistory workHistory) {
        this.id = workHistory.getId();
        this.workHistory = workHistory.getWorkHistory();
        this.workHistoryPeriod = workHistory.getWorkHistoryPeriod();
    }
}
