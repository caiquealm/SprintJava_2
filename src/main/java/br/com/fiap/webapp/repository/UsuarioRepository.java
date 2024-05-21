package br.com.fiap.webapp.repository;

import br.com.fiap.webapp.entity.Acessibilidade;
import br.com.fiap.webapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{}
