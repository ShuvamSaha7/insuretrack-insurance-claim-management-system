package in.strikes.InsuranceClaimManagementSystem.service;

import in.strikes.InsuranceClaimManagementSystem.entity.*;
import in.strikes.InsuranceClaimManagementSystem.repository.UserRepository;
import in.strikes.InsuranceClaimManagementSystem.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    public String register(RegisterRequest request) {

        if (userRepository
                .findByUsername(request.getUsername())
                .isPresent()) {

            throw new RuntimeException(
                    "Username already exists!"
            );
        }

        User user = new User();

        user.setUsername(request.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(
                request.getRole().toUpperCase()
        );

        userRepository.save(user);

        return "User registered successfully!";
    }


    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found!"
                        )
                );

        String token =
                jwtService.generateToken(
                        user.getUsername(),
                        user.getRole()
                );

        return new AuthResponse(
                token,
                user.getUsername(),
                user.getRole()
        );
    }
}