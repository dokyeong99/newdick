//package com.newnomal.newdick.domain.response;
//
//import com.newnomal.newdick.domain.entity.CareReservation;
//import com.newnomal.newdick.domain.entity.CareTaker;
//import com.newnomal.newdick.domain.entity.User;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//public class CareTakerResponse {
//    private Long id;
//    private String name;
//    private String gender;
//    private LocalDate birthDate;
//    private String height;
//    private String weight;
//    private List<CareReservationResponse> careReservation;
//
//    public CareTakerResponse(CareTaker careTaker) {
//        this.id = careTaker.getId();
//        this.name = careTaker.getName();
//        this.gender = careTaker.getGender();
//        this.birthDate = careTaker.getBirthDate();
//        this.height = careTaker.getHeight();
//        this.weight = careTaker.getWeight();
//        this.careReservation = careTaker.getCareReservation().
//                stream().map(CareReservationResponse::new).toList();
//    }
//}
