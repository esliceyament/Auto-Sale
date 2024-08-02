package com.example.car_sale.service;

import com.example.car_sale.entity.User;
import com.example.car_sale.enums.Role;
import com.example.car_sale.exception.NotFoundException;
import com.example.car_sale.payload.AuthenticationPayload;
import com.example.car_sale.payload.RegisterPayload;
import com.example.car_sale.repository.UserRepository;
import com.example.car_sale.response.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterPayload payload) {
        var user = User.builder()
                .name(payload.getName())
                .email(payload.getEmail())
                .passW(passwordEncoder.encode(payload.getPassW()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationPayload payload) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        payload.getEmail(),
                        payload.getPassW()
                )
        );
        var user = userRepository.findByEmail(payload.getEmail())
                .orElseThrow(() -> new NotFoundException("Not found!"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
