package org.example.nishiki_koi_shop.controller;


import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.dto.UserDto;
import org.example.nishiki_koi_shop.model.payload.ChangePasswordForm;
import org.example.nishiki_koi_shop.model.payload.UserForm;
import org.example.nishiki_koi_shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/myInfo")
    public ResponseEntity<UserDto> getMyInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserForm form) {
        return ResponseEntity.ok(userService.updateUser(id, form));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordForm form, Principal connectedUser) {
        return ResponseEntity.ok(userService.changePassword(form, connectedUser));
    }


}
