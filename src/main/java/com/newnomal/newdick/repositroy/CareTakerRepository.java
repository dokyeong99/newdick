//package com.newnomal.newdick.repositroy;
//
//import com.newnomal.newdick.domain.entity.CareTaker;
//import com.newnomal.newdick.domain.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface CareTakerRepository extends JpaRepository<CareTaker, Long> {
//    Optional<List<CareTaker>> getCareTakersByUser(User user);
//    Optional<List<CareTaker>> getCareTakerById( Long id);
//}
//
