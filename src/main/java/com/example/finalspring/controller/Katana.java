package com.example.finalspring.controller;
import com.example.finalspring.dto.KatanaDto;
import com.example.finalspring.service.KatanaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/katana")

public class Katana {
    private final KatanaService katanaService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(katanaService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(katanaService.getById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addKatana(@RequestBody KatanaDto katanaDto){
        return new ResponseEntity<>(katanaService.addKatana(katanaDto),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateKatana(@PathVariable(name = "id")Long id, @RequestBody KatanaDto katanaDto){
        return new ResponseEntity<>(katanaService.updateKatana(id, katanaDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteKatana(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(katanaService.deleteKatana(id),HttpStatus.OK);
    }
}
