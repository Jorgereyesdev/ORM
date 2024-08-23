package com.ormproyect.demo.controllers;

import com.ormproyect.demo.entities.Session;
import com.ormproyect.demo.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Optional<Session> session = sessionService.getSessionById(id);
        return session.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionService.saveSession(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session session) {
        if (!sessionService.getSessionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        session.setId(id);
        return ResponseEntity.ok(sessionService.saveSession(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        if (!sessionService.getSessionById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}