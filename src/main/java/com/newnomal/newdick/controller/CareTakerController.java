//package com.newnomal.newdick.controller;
//
//
//import com.newnomal.newdick.common.RestResult;
//import com.newnomal.newdick.domain.entity.CareTaker;
//import com.newnomal.newdick.domain.request.CareTakerRequest;
//import com.newnomal.newdick.service.CareTakerService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import retrofit2.http.Path;
//
//import java.util.List;
//import java.util.Objects;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("api/v1/caretaker")
//public class CareTakerController {
//    private final CareTakerService careTakerService;
//
//    @GetMapping("/userId/{userId}")
//    public ResponseEntity<RestResult<Object>> getCareTakersByUserId(@PathVariable Long userId) {
//        return careTakerService.getCareTakersByUserId(userId);
//    }
//
//    @GetMapping("careTakerId/{careTakerId}")
//    public ResponseEntity<RestResult<Object>> getCareTakerByCareTakerId(@PathVariable Long careTakerId) {
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<RestResult<Object>> addCareTaker(@RequestBody CareTakerRequest careTakerRequest) {
//        return careTakerService.addCareTaker(careTakerRequest);
//    }
//
//}
