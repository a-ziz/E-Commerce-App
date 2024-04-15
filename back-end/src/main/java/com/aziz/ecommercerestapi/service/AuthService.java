package com.aziz.ecommercerestapi.service;

import com.aziz.ecommercerestapi.payload.LoginDto;
import com.aziz.ecommercerestapi.payload.LoginResponse;
import com.aziz.ecommercerestapi.payload.RegisterDto;
import com.aziz.ecommercerestapi.payload.RegisterResponse;

public interface AuthService {
  LoginResponse login(LoginDto loginDto);
  RegisterResponse register(RegisterDto registerDto);
}
