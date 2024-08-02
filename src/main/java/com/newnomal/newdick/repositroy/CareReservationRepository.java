package com.newnomal.newdick.repositroy;

import com.newnomal.newdick.domain.entity.CareReservation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CareReservationRepository extends JpaRepository<CareReservation, Long> {
    @EntityGraph(attributePaths = {"caregiver", "user"})
    Page<CareReservation> findByUserId(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {"caregiver", "user"})
    Page<CareReservation> findByCaregiverId(Long caregiverId, Pageable pageable);

    @EntityGraph(attributePaths = {"caregiver", "user"})
    Optional<CareReservation> findById(@NotNull Long id);

    @EntityGraph(attributePaths = {"caregiver", "user"})
    Page<CareReservation> findByCaregiverIdAndState(Long caregiverId,Integer state ,Pageable pageable);

    @EntityGraph(attributePaths = {"caregiver", "user"})
    Page<CareReservation> findByState(Integer state ,Pageable pageable);

}