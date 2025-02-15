package com.technolab.auth.controller;

import com.technolab.auth.dto.ErrorResDto;
import com.technolab.auth.dto.LoginReqDto;
import com.technolab.auth.dto.LoginResDto;
import com.technolab.auth.models.User;
import com.technolab.auth.repositories.UserRepository;
import com.technolab.auth.security.JwtUtil;
import com.technolab.auth.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


    private JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody LoginReqDto loginReq)  {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword()));
            String email = authentication.getName();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(
                            ()->new BadCredentialsException("User not found"));

           /* if (!new BCryptPasswordEncoder().matches(loginReq.getPassword(), user.getPassword())) {
                throw new BadCredentialsException("Wrong password");
            }*/

            String token = jwtUtil.createToken(user);
            LoginResDto loginRes = new LoginResDto(email,token);

            return ResponseEntity.ok(loginRes);

        }catch (BadCredentialsException e){
            ErrorResDto errorResponse = new ErrorResDto(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResDto errorResponse = new ErrorResDto(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
