package com.uol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.uol.service.domain.Jogador;

@Component
public interface JogadorRepository extends JpaRepository<Jogador, Long>{

}
