package org.example.nishiki_koi_shop.controller;

import lombok.RequiredArgsConstructor;
import org.example.nishiki_koi_shop.model.payload.SignInForm;
import org.example.nishiki_koi_shop.model.payload.SignUpForm;
import org.example.nishiki_koi_shop.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SignInForm form){
        return ResponseEntity.ok(authService.login(form));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody SignUpForm form){
        return ResponseEntity.ok(authService.register(form));
    }

    @GetMapping("/refresh")
    public ResponseEntity refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken){
        return ResponseEntity.ok(authService.refreshJWT(refreshToken));
    }


}