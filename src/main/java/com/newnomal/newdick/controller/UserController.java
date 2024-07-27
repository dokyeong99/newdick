package com.newnomal.newdick.controller;


import com.newnomal.newdick.common.RestResult;
import com.newnomal.newdick.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<RestResult<Object>> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/userId/{userId}")
    public ResponseEntity<RestResult<Object>> deleteUserById(@PathVariable Long userId) {
        return userService.deleteUserById(userId);
    }


}
