package br.com.fiap.webapp.repository;

import br.com.fiap.webapp.entity.Feedback;
import br.com.fiap.webapp.entity.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{}
