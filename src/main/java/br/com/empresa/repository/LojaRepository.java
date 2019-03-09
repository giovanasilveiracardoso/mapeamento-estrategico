package br.com.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.empresa.entity.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long> {
}
