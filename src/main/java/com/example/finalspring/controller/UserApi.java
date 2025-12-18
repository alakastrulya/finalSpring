package com.example.finalspring.controller;

import com.example.finalspring.entity.User;
import com.example.finalspring.service.MyUserService;
import com.example.finalspring.service.SamuraiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserApi {
    private final MyUserService myUserService;
    private final SamuraiService samuraiService;

    @GetMapping
    public String getAuth(){
        return "hello";
    }
    @PostMapping("/registr")
    public String getRegistr(@RequestBody User user){
        myUserService.registr(user);
        return "done";
    }
    @GetMapping("/samurai")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(samuraiService.getAll(), HttpStatus.OK);
    }
}
