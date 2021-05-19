package com.example.demo.model.repository;

import com.example.demo.model.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

    Optional<Terminal> findByLogic(Integer logic);
}
