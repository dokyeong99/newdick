package com.newnomal.newdick.domain.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "CaregiverWorkHistoryTable")
@Setter
public class CaregiverWorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Caregiver caregiverId;
    private String workHistory;
    private Integer workHistoryPeriod;
}
