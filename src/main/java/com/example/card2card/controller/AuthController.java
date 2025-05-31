package com.example.card2card.controller;


import com.example.card2card.dto.auth.LoginRequest;
import com.example.card2card.dto.auth.LoginResponse;
import com.example.card2card.entity.User;
import com.example.card2card.repository.UserRepository;
import com.example.card2card.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user authentication, handling login and JWT token generation.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Authenticates user credentials and returns JWT token.
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        logger.info("درخواست لاگین برای کاربر: {}", request.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername());
        logger.info("توکن تولید شد برای کاربر {}: {}", request.getUsername(), token);

        return new LoginResponse(token);
    }
}
