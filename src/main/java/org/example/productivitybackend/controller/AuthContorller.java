package org.example.productivitybackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.productivitybackend.config.CustomUserDetails;
import org.example.productivitybackend.entity.DTO.AuthResponse;
import org.example.productivitybackend.entity.DTO.RegisterRequest;
import org.example.productivitybackend.util.JwtUtil;
import org.example.productivitybackend.entity.DTO.AuthRequest;
import org.example.productivitybackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthContorller  {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody RegisterRequest userDto) {
        if (userService.existsByUsername(userDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }
        userService.signUp(userDto);
        return ResponseEntity.ok("User registered successfully!");
    }
    @GetMapping("/getCurrentUser")
    public ResponseEntity<?> getCurrentUser() {

        return ResponseEntity.ok(userService.getCurrentUser());
    }
}




