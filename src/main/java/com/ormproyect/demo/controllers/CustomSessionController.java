package com.ormproyect.demo.controllers;

import com.ormproyect.demo.entities.CustomSession;
import com.ormproyect.demo.services.CustomSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/custom-sessions")
public class CustomSessionController {

    @Autowired
    private CustomSessionService customSessionService;

    @GetMapping
    public List<CustomSession> getAllCustomSessions() {
        return customSessionService.getAllCustomSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomSession> getCustomSessionById(@PathVariable Long id) {
        Optional<CustomSession> customSession = customSessionService.getCustomSessionById(id);
        return customSession.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CustomSession createCustomSession(@RequestBody CustomSession customSession) {
        return customSessionService.saveCustomSession(customSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomSession> updateCustomSession(@PathVariable Long id, @RequestBody CustomSession customSession) {
        if (!customSessionService.getCustomSessionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customSession.setId(id);
        return ResponseEntity.ok(customSessionService.saveCustomSession(customSession));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomSession(@PathVariable Long id) {
        if (!customSessionService.getCustomSessionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        customSessionService.deleteCustomSession(id);
        return ResponseEntity.noContent().build();
    }
}
