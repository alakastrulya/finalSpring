package com.example.finalspring.controller;

import com.example.finalspring.dto.DojoDto;
import com.example.finalspring.service.DojoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/dojo")
@RequiredArgsConstructor
public class Dojo {
 private final DojoService dojoService;

 @GetMapping()
    public ResponseEntity<?> getAll(){
     return new ResponseEntity<>(dojoService.getAll(), HttpStatus.OK);
 }
 @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable (name = "id") Long id){
     return new ResponseEntity<>(dojoService.getById(id),HttpStatus.OK);
 }
 @PostMapping()
    public ResponseEntity<?> add(@RequestBody DojoDto dojoDto){
     return new ResponseEntity<>(dojoService.addDojo(dojoDto), HttpStatus.OK);
 }

 @PutMapping("/{id}")
    public ResponseEntity<?> updateDojo(@PathVariable(name = "id") Long id, @RequestBody DojoDto dojoDto){
     return new ResponseEntity<>(dojoService.updateDojo(id, dojoDto), HttpStatus.OK);
 }

 @DeleteMapping("/{id}")
    public ResponseEntity<?> deletDojo(@PathVariable(name = "id")Long id){
     return new ResponseEntity<>(dojoService.deleteDojo(id), HttpStatus.OK);
 }
}
