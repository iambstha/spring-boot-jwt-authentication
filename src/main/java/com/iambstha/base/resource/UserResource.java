package com.iambstha.base.resource;

import com.iambstha.base.dto.UserReqDto;
import com.iambstha.base.dto.UserResDto;
import com.iambstha.base.entity.User;
import com.iambstha.base.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserResource {

    @Autowired
    private final UserService userService;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody User user) {
        Long id = userService.save(user);
        String message = "User with id " + id + " successfully created.";
        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResDto> login(@RequestBody UserReqDto userReqDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userReqDto.getUsername(), userReqDto.getPassword()));
        return ResponseEntity.ok(userService.login(userReqDto));
    }

    @PostMapping("/get")
    public ResponseEntity<String> test(Principal principal) {
        return ResponseEntity.ok("You are accessing as: " + principal.getName());
    }

}
