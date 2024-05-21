package br.com.fiap.webapp.repository;

import br.com.fiap.webapp.entity.Licao;
import br.com.fiap.webapp.entity.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicaoRepository extends JpaRepository<Licao, Long>{}
