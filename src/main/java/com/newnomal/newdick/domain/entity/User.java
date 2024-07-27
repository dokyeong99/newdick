package com.newnomal.newdick.domain.entity;


import com.newnomal.newdick.domain.request.UserSignUpRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
@Table(name = "UserTable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String city;
    @OneToMany(mappedBy  = "user")
    private List<CareReservation> careReservations;

    public User(UserSignUpRequest userSignUpRequest){
        this.email = userSignUpRequest.getEmail();
        this.password = userSignUpRequest.getPassword();
        this.name = userSignUpRequest.getName();
        this.phone = userSignUpRequest.getPhone();
        this.gender = userSignUpRequest.getGender();
        this.birthDate = LocalDate.parse(userSignUpRequest.getBirthDate());
    }

}
