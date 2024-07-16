package com.newnomal.newdick.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CertificationTable")
public class Certification {//국가 발급 관리 공공기관이라는 가정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String authority;
    private String name;
    private String publishedDate;
    private String certificationNumber;
    @ManyToOne
    private Caregiver caregiver;
}
