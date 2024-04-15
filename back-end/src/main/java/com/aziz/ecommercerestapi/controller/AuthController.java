package com.aziz.ecommercerestapi.controller;

import com.aziz.ecommercerestapi.payload.LoginDto;
import com.aziz.ecommercerestapi.payload.LoginResponse;
import com.aziz.ecommercerestapi.payload.RegisterDto;
import com.aziz.ecommercerestapi.payload.RegisterResponse;
import com.aziz.ecommercerestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping(value = {"/login", "/signin"})
  public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDto){
    var response = authService.login(loginDto);
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = {"/register", "signup"})
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDto registerDto){
    var response = authService.register(registerDto);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }


}
