package com.ormproyect.demo.repositories;

import com.ormproyect.demo.entities.CustomSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSessionRepository extends JpaRepository<CustomSession, Long> {
}
