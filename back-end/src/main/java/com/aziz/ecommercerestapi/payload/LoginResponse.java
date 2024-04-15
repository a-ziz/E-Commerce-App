package com.aziz.ecommercerestapi.payload;

import com.aziz.ecommercerestapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
  private boolean error;
  private String message;
  private String token;
//  private User user;
}
