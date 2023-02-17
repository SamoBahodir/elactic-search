package com.example.api;

import com.example.model.user.AuthenticationRequest;
import com.example.model.user.User;
import com.example.model.user.UserService;
import com.example.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            String username = request.getUsername();
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username" + username + " not found");
            }
            String token = tokenProvider.createToken(username, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok().body(response);
        } catch (AuthenticationException ae) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
