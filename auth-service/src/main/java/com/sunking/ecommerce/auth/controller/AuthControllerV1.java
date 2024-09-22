package com.sunking.ecommerce.auth.controller;

import com.sunking.ecommerce.auth.dto.response.ApiResponse;
import com.sunking.ecommerce.auth.dto.request.LoginRequestDto;
import com.sunking.ecommerce.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthControllerV1 {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<ApiResponse> login(@RequestBody @Validated LoginRequestDto loginRequest) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Login successful", authService.login(loginRequest)));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(
                new ApiResponse(false, ex.getMessage(), null), HttpStatus.UNAUTHORIZED);
    }

}

