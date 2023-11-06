package com.andreiradu.gamestore.api.controller;

import com.andreiradu.gamestore.api.model.User;
import com.andreiradu.gamestore.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/basic-auth")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getEmail(), user.getPassword());
        User loginUser = userService.findUserByEmail(user.getEmail());
        HttpHeaders basicHeader = getBasicAuthHeader(user);
        return new ResponseEntity<>(loginUser, basicHeader, HttpStatus.OK);
    }

    private HttpHeaders getBasicAuthHeader(User user) {
        String auth = user.getEmail() + ":" + user.getPassword();
        byte[] authBytes = auth.getBytes();
        byte[] base64AuthBytes = Base64.encodeBase64(authBytes, false);
        String base64Auth = new String(base64AuthBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Auth);
        return headers;
    }

    private void authenticate(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
