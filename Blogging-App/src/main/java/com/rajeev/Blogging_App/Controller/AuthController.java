package com.rajeev.Blogging_App.Controller;


import com.rajeev.Blogging_App.Payloads.ApiResponse;
import com.rajeev.Blogging_App.Payloads.JWTAuthRequest;
import com.rajeev.Blogging_App.Payloads.JwtAuthResponse;
import com.rajeev.Blogging_App.Payloads.UserDTO;
import com.rajeev.Blogging_App.Security.JwtService;
import com.rajeev.Blogging_App.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/auth")
@Tag(name = "Authentication Api's" ,description = "For Authentication")

public class  AuthController{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;


    //User Login
    @Operation(summary = "After Registration Login By UserName(Email) and Password for getting the JWT Token")
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


    //Register new User.
    @Operation(summary = "Before Login send user details Like(name , email , password , about & role will be assigned automatically(Don't sen role))")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto){
        UserDTO user = userService.registerNewUser(userDto);

        return new ResponseEntity<>(user , HttpStatus.CREATED);
    }
}
