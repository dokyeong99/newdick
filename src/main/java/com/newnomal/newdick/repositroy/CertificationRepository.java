package com.newnomal.newdick.repositroy;

import com.newnomal.newdick.domain.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}
