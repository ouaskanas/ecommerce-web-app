package com.ouaskanas.commerce.dto.request;

import com.ouaskanas.commerce.model.enums.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private int age;
}
