package com.newnomal.newdick.repositroy;

import com.newnomal.newdick.domain.entity.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long> {
    List<WorkHistory> findByCaregiverId(Long caregiverId);
}
