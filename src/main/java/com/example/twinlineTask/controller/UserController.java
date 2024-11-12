package com.example.twinlineTask.controller;

import com.example.twinlineTask.dto.ResponseDto;
import com.example.twinlineTask.dto.SignUpAndLoginDto;
import com.example.twinlineTask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping("/signup")
        public ResponseEntity<ResponseDto> signup(@RequestBody @Valid SignUpAndLoginDto signUpAndLoginDto) {
        return ResponseEntity.ok(userService.signup(signUpAndLoginDto.getMobileNumber(), signUpAndLoginDto.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody SignUpAndLoginDto signUpAndLoginDto) {
        return ResponseEntity.ok(userService.login(signUpAndLoginDto.getMobileNumber(), signUpAndLoginDto.getPassword()));
    }
}
