package com.sunking.ecommerce.auth.dto.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginRequestDto {
    @NonNull
    private String email;
    @NonNull
    private String password;
}
