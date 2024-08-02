package com.newnomal.newdick.domain.response;

import com.newnomal.newdick.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String city;
    private List<CareReservationResponse> careReservations;

    public UserResponse(User user, String noData){
        this.id= user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.gender = user.getGender();
        this.birthDate = user.getBirthDate();
        this.address = user.getAddress();
        this.city = user.getCity();
        this.careReservations = user.getCareReservations().stream().map(CareReservationResponse::new).toList();
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
    }
}
