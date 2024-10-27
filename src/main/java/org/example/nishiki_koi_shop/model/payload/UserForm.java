package org.example.nishiki_koi_shop.model.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Data;
import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.entity.User;

import java.time.LocalDate;

@Builder
@Data
public class UserForm {
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String address;

    public static UserForm from(User user) {
        return UserForm.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhoneNumber())
                .address(user.getAddress())
                .username(user.getUsername())
                .build();
    }

}