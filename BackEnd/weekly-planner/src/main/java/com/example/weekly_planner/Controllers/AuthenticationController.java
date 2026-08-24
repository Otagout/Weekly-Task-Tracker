package com.example.weekly_planner.Controllers;

import com.example.weekly_planner.Respones.LoginResponse;
import com.example.weekly_planner.dto.LoginUserDto;
import com.example.weekly_planner.dto.RegisterUserDto;
import com.example.weekly_planner.dto.VerifyUserDto;
import com.example.weekly_planner.entity.User;
import com.example.weekly_planner.sevice.AuthenticationService;
import com.example.weekly_planner.sevice.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
private final JwtService jwtService;
private final AuthenticationService authenticationService;

public AuthenticationController(JwtService jwtService  , AuthenticationService authenticationService){
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
}

@PostMapping("/signup")

    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto){
    User registerdUser = authenticationService.singUp(registerUserDto);
    return ResponseEntity.ok(registerdUser);

}

@PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
    User authenticatedUser = authenticationService.authenticate(loginUserDto);
    String jwtToken = jwtService.generateToken(authenticatedUser);
    LoginResponse loginResponse  = new LoginResponse(jwtToken , jwtService.getExpirationTime());
    return ResponseEntity.ok(loginResponse);

}

@PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto){
    try{
        authenticationService.VerifyUser(verifyUserDto);
        return ResponseEntity.ok("account Verified successfully");

    }catch (RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

@PostMapping("/resend")
public ResponseEntity<?> resendVerificationCode(@RequestParam String email){
    try {
        authenticationService.resetVerificationCode(email);
        return ResponseEntity.ok("Verification code sent");
    }catch (RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());


    }
}




}
