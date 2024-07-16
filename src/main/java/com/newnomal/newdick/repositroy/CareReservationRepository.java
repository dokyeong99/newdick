package com.newnomal.newdick.repositroy;

import com.newnomal.newdick.domain.entity.CareReservation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CareReservationRepository extends JpaRepository<CareReservation, Long> {
    @EntityGraph(attributePaths = {"caregiver", "user"})
    List<CareReservation> findByUserId(Long userId);
    @EntityGraph(attributePaths = {"caregiver", "user"})
    List<CareReservation> findByCaregiverId(Long caregiverId);
    @EntityGraph(attributePaths = {"caregiver", "user"})
    Optional<CareReservation> findById(@NotNull Long id);

}