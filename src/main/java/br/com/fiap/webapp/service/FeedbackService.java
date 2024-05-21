package br.com.fiap.webapp.service;

import br.com.fiap.webapp.dto.request.FeedbackRequest;
import br.com.fiap.webapp.dto.request.InstrumentoRequest;
import br.com.fiap.webapp.dto.response.FeedbackResponse;
import br.com.fiap.webapp.dto.response.InstrumentoResponse;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Feedback;
import br.com.fiap.webapp.entity.Instrumento;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.repository.FeedbackRepository;
import br.com.fiap.webapp.repository.InstrumentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FeedbackService implements  ServiceDTO<Feedback, FeedbackRequest, FeedbackResponse>{

    @Autowired
    private FeedbackRepository repo;

    @Autowired
    private UsuarioService usuarioService;


    @Override
    public Collection<Feedback> findAll(Example<Feedback> example) {
        return repo.findAll(example);
    }

    @Override
    public Feedback findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Feedback save(Feedback e) {
        return repo.save(e);
    }

    @Override
    public Feedback toEntity(FeedbackRequest dto) {
        Usuario usuario = usuarioService.findById(dto.usuario().id());
        return Feedback.builder()
                .textFeedback(dto.textFeedback())
                .resposta(dto.resposta())
                .usuario(usuario)
                .build();
    }

    @Override
    public FeedbackResponse toResponse(Feedback e) {
        return FeedbackResponse.builder()
                .id(e.getId())
                .textFeedback(e.getTextFeedback())
                .resposta(e.getResposta())
                .usuario(usuarioService.toResponse(e.getUsuario()))
                .build();
    }
}
