package com.carledwin.ti.contabil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carledwin.ti.contabil.model.Grupo;

public interface Permissoes  extends JpaRepository<Permissao, Long> {

	List<Permissao> findByGruposIn(Grupo grupo);
}
