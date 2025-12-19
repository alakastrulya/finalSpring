package com.example.finalspring.controller;

import com.example.finalspring.entity.User;
import com.example.finalspring.service.DojoService;
import com.example.finalspring.service.KatanaService;
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
    private final DojoService dojoService;
    private final KatanaService katanaService;


    @GetMapping
    public String getAuth(){
        return "MochiMochi!!";
    }
    @PostMapping("/registr")
    public String getRegistr(@RequestBody User user){
        myUserService.registr(user);
        return "done";
    }

    @GetMapping("/create-admin")
    public String createAdmin() {
        myUserService.createAdminIfNotExists();
        return "Username: admin. Password: admin123";
    }

    @GetMapping("/samurai")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(samuraiService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/dojo")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<?> getAllDojos() {
        return new ResponseEntity<>(dojoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/katana")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<?> getAllKatanas() {
        return ResponseEntity.ok(katanaService.getAll());
    }
}

