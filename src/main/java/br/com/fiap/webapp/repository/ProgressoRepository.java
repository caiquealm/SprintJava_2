package br.com.fiap.webapp.repository;

import br.com.fiap.webapp.entity.Progresso;
import br.com.fiap.webapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressoRepository extends JpaRepository<Progresso, Long>{}
