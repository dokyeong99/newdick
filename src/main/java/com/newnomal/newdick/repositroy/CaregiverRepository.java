package com.newnomal.newdick.repositroy;

import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.Caregiver;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface CaregiverRepository extends JpaRepository<Caregiver, Long> {
    public Optional<Caregiver> findByEmail(String email);
    public Optional<Caregiver> findByEmailAndPassword(String email, String password);
    @NotNull
    public Optional<Caregiver> findById(@NotNull Long caregiverId);
}
