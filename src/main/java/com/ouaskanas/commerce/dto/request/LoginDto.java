package com.ouaskanas.commerce.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginDto {
    private String email;
    private String password;
}
