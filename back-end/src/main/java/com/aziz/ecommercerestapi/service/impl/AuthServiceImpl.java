package com.aziz.ecommercerestapi.service.impl;

import com.aziz.ecommercerestapi.entity.Role;
import com.aziz.ecommercerestapi.entity.User;
import com.aziz.ecommercerestapi.payload.LoginDto;
import com.aziz.ecommercerestapi.payload.LoginResponse;
import com.aziz.ecommercerestapi.payload.RegisterDto;
import com.aziz.ecommercerestapi.payload.RegisterResponse;
import com.aziz.ecommercerestapi.repository.RoleRepository;
import com.aziz.ecommercerestapi.repository.UserRepository;
import com.aziz.ecommercerestapi.security.JwtTokenProvider;
import com.aziz.ecommercerestapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  public LoginResponse login(LoginDto loginDto) {
    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsernameOrEmail(), loginDto.getPassword()
            ));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String token = jwtTokenProvider.generateToken(authentication);
    LoginResponse response = new LoginResponse();
    response.setToken(token);
    response.setMessage("login successful");
    response.setError(false);

    return response;
  }

  @Override
  public RegisterResponse register(RegisterDto registerDto) {
    // check if username exists in database
    if (userRepository.existsByUsername(registerDto.getUsername())){
      throw new RuntimeException("username already exists!");
    }

    // check if email exist in database
    if (userRepository.existsByEmail(registerDto.getEmail())){
      throw new RuntimeException("email already exist!");
    }

    User user = new User();
    user.setName(registerDto.getName());
    user.setUsername(registerDto.getUsername());
    user.setEmail(registerDto.getEmail());
    user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

    Set<Role> roles = new HashSet<>();
    // no value present at Optional.java:143
    Role userRole = roleRepository.findByName("ROLE_USER").get();
    roles.add(userRole);
    user.setRoles(roles);

//    userRepository.save(user);
    User savedUser = userRepository.save(user);

    RegisterResponse response = new RegisterResponse();
    response.setError(false);
    response.setMessage("register successful");
    response.setUser(savedUser);

    return response;
  }
}
