package com.example.demo.controller;


import com.example.demo.security.TokenProvider;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.util.JwtRespone;
import com.example.demo.util.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginModel loginModel){
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginModel.getUsername(),
                        loginModel.getPassword()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginModel.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(userDetails);
        return ResponseEntity.ok(new JwtRespone(token));
    }

}
