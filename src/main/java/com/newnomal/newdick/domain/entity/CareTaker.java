//package com.newnomal.newdick.domain.entity;
//
//
//import com.newnomal.newdick.domain.request.CareTakerRequest;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Getter
//@Table(name = "CareTakerTable")
//@Setter
//public class CareTaker {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    private String gender;
//    private LocalDate birthDate;
//    private String height;
//    private String weight;
//    @OneToMany(mappedBy = "careTaker",fetch = FetchType.LAZY)
//    private List<CareReservation> careReservation;
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    public CareTaker(CareTakerRequest careTakerRequest) {
//        this.name = careTakerRequest.getName();
//        this.gender = careTakerRequest.getGender();
//        this.birthDate = careTakerRequest.getBirthDate();
//        this.height = careTakerRequest.getHeight();
//        this.weight = careTakerRequest.getWeight();
//    }
//
//}
