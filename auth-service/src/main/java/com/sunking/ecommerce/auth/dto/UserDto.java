package com.sunking.ecommerce.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunking.ecommerce.auth.entity.UserEntity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;

    public UserDto(UserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
    }
}
