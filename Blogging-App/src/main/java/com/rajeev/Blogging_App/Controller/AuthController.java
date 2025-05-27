package com.rajeev.Blogging_App.Controller;


import com.rajeev.Blogging_App.Exception.ResourceNotFoundException;
import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.JWTAuthRequest;
import com.rajeev.Blogging_App.Payloads.JwtAuthResponse;
import com.rajeev.Blogging_App.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class  AuthController{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authManager;


    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JWTAuthRequest jwtAuthRequest) throws Exception{

        try {
            Authentication authentication =
                    authManager
                            .authenticate(new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword()));

            String token = "";
            if (authentication.isAuthenticated()) {
                token = jwtService.generateToken(jwtAuthRequest.getUsername());
                return new ResponseEntity<>(new JwtAuthResponse(token), HttpStatus.OK);
            }

        }catch (BadCredentialsException ex) {
            throw ex;
        }
        return new ResponseEntity<>(new ApiResponse(), HttpStatus.UNAUTHORIZED);
    }
}
