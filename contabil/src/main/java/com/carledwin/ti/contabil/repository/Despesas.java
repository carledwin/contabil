package com.carledwin.ti.contabil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carledwin.ti.contabil.model.Despesa;

public interface Despesas extends JpaRepository<Despesa, Long> {

	public List<Despesa> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);
}
