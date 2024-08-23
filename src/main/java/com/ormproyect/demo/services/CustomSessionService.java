package com.ormproyect.demo.services;

import com.ormproyect.demo.entities.CustomSession;
import com.ormproyect.demo.repositories.CustomSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomSessionService {

    @Autowired
    private CustomSessionRepository customSessionRepository;

    public List<CustomSession> getAllCustomSessions() {
        return customSessionRepository.findAll();
    }

    public Optional<CustomSession> getCustomSessionById(Long id) {
        return customSessionRepository.findById(id);
    }

    public CustomSession saveCustomSession(CustomSession customSession) {
        return customSessionRepository.save(customSession);
    }

    public void deleteCustomSession(Long id) {
        customSessionRepository.deleteById(id);
    }
}