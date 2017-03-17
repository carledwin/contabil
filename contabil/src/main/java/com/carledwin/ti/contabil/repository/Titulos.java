package com.carledwin.ti.contabil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carledwin.ti.contabil.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long> {

	public List<Titulo> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
}
