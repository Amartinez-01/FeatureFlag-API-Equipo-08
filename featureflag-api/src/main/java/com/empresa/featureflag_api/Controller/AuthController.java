package com.empresa.featureflag_api.Controller;

import com.empresa.featureflag_api.Dto.AuthResponse;
import com.empresa.featureflag_api.Dto.LoginRequest;
import com.empresa.featureflag_api.Dto.RegisterRequest;
import com.empresa.featureflag_api.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<Void>register(@RequestBody @Valid RegisterRequest request){
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody @Valid LoginRequest request){
        return ResponseEntity.ok(authService.Login(request));

    }



}
