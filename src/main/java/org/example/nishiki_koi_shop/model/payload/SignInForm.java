package org.example.nishiki_koi_shop.model.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInForm {
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 6)
    private String password;
}