package com.example.finalspring.controller;
import com.example.finalspring.dto.SamuraiDto;
import com.example.finalspring.service.SamuraiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("samurai")

public class Samurai {
    private final SamuraiService samuraiService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(samuraiService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(samuraiService.getById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addSamurai(@RequestBody SamuraiDto samuraiDto){
        return new ResponseEntity<>(samuraiService.addSamurai(samuraiDto),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSamurai(@PathVariable(name = "id")Long id, @RequestBody SamuraiDto samuraiDto){
        return new ResponseEntity<>(samuraiService.updateSamurai(id, samuraiDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSamurai(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(samuraiService.deleteSamurai(id),HttpStatus.OK);
    }
}
