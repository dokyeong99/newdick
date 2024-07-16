//package com.newnomal.newdick.service;
//
//import com.newnomal.newdick.common.RestResult;
//import com.newnomal.newdick.domain.entity.CareTaker;
//import com.newnomal.newdick.domain.entity.User;
//import com.newnomal.newdick.domain.request.CareTakerRequest;
//import com.newnomal.newdick.domain.response.CareTakerResponse;
//import com.newnomal.newdick.repositroy.CareTakerRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class CareTakerService {
//    private CareTakerRepository careTakerRepository;
//
//    public ResponseEntity<RestResult<Object>> addCareTaker(CareTakerRequest careTakerRequest) {
//        CareTaker careTaker = careTakerRepository.save(new CareTaker(careTakerRequest));
//        return ResponseEntity.ok(new RestResult<>("Success", careTaker.getId()));
//    }
//
//    public ResponseEntity<RestResult<Object>> getCareTakersByUserId( Long userId) {
//        Optional<List<CareTaker>> careTakers = careTakerRepository.getCareTakersByUser(User.builder().id(userId).build());
//        if (careTakers.isEmpty()) {
//            return ResponseEntity.ok(new RestResult<>("No Data","No Data"));
//        }
//        List<CareTakerResponse> careTakerResponse = careTakers.get().stream().map(CareTakerResponse::new).toList();
//        return ResponseEntity.ok(new RestResult<>("Success", careTakerResponse));
//    }
//
//    @GetMapping("careTakerId/{careTakerId}")
//    public List<CareTaker> getCareTakersByCareTakerId(@PathVariable Long careTakerId) {
//        return null;
//    }
//
//}
