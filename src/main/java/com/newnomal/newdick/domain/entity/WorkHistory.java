package com.newnomal.newdick.domain.entity;


import com.newnomal.newdick.domain.request.WorkHistoryRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "CaregiverWorkHistoryTable")
@Setter
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Caregiver caregiver;
    private String workHistory;
    private Integer workHistoryPeriod;

    public WorkHistory(WorkHistoryRequest workHistoryRequest){
        this.workHistory = workHistoryRequest.getWorkHistory();
        this.workHistoryPeriod = workHistoryRequest.getWorkHistoryPeriod();
        this.caregiver = Caregiver.builder().id(workHistoryRequest.getCaregiverId()).build();
    }
}
