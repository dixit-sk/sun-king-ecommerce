package com.sunking.ecommerce.auth.service;

import com.sunking.ecommerce.auth.dto.request.LoginRequestDto;
import com.sunking.ecommerce.auth.dto.response.LoginResponseDto;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequest);

}
