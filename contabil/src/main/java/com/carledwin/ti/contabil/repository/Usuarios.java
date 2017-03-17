package com.carledwin.ti.contabil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carledwin.ti.contabil.model.Usuario;

public interface Usuarios  extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

}
