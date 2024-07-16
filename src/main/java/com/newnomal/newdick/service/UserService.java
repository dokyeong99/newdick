package com.newnomal.newdick.service;


import com.newnomal.newdick.common.RestError;
import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.domain.entity.User;
import com.newnomal.newdick.domain.response.UserResponse;
import com.newnomal.newdick.repositroy.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<RestResult<Object>> getUserById(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.getUserById(userId);
        if (userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("No Data", "해당 아이디로 정보를 찾을 수 없습니다")));

        }
        UserResponse userResponse = new UserResponse(userOptional.get(),"생성자 분별용 null Text");
        return ResponseEntity.ok().body(new RestResult<>("SUCCESS",userResponse));
    }

    public ResponseEntity<RestResult<Object>> deleteUserById(@PathVariable Long userId) {
        try {
            userRepository.deleteById(userId);
            return ResponseEntity.ok(new RestResult<>("SUCCESS", "Delete Clear"));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",
                            new RestError("Delete Failed", exception.getMessage())));
        }
    }
}
