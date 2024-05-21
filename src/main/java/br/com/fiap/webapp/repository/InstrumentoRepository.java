package br.com.fiap.webapp.repository;

import br.com.fiap.webapp.entity.Instrumento;
import br.com.fiap.webapp.entity.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Long>{}
