package com.carledwin.ti.contabil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carledwin.ti.contabil.model.Grupo;
import com.carledwin.ti.contabil.model.Usuario;

public interface Grupos extends JpaRepository<Grupo, Long> {

	List<Grupo> findByUsuariosIn(Usuario usuario);

}
