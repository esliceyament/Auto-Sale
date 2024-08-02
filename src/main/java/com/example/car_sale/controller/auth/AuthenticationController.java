package com.example.car_sale.controller.auth;

import com.example.car_sale.payload.AuthenticationPayload;
import com.example.car_sale.payload.RegisterPayload;
import com.example.car_sale.response.AuthenticationResponse;
import com.example.car_sale.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterPayload payload
    ) {
        return ResponseEntity.ok(authenticationService.register(payload));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationPayload payload
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(payload));
    }

}
