package br.com.fiap.webapp.service;

import br.com.fiap.webapp.dto.request.ProgressoRequest;
import br.com.fiap.webapp.dto.response.ProgressoResponse;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Progresso;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.repository.ProgressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProgressoService implements ServiceDTO<Progresso, ProgressoRequest, ProgressoResponse> {

    @Autowired
    private ProgressoRepository repo;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Collection<Progresso> findAll(Example<Progresso> example) {
        return repo.findAll(example);
    }

    @Override
    public Progresso findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Progresso save(Progresso e) {
        return repo.save(e);
    }

    @Override
    public Progresso toEntity(ProgressoRequest dto) {
        Usuario usuario = usuarioService.findById(dto.usuario().id());

        return Progresso.builder()
                .progressao(dto.progressao())
                .pontuacao(dto.pontuacao())
                .usuario(usuario)
                .build();
    }

    @Override
    public ProgressoResponse toResponse(Progresso e) {
        return ProgressoResponse.builder()
                .id(e.getId())
                .progressao(e.getProgressao())
                .pontuacao(e.getPontuacao())
                .usuario(usuarioService.toResponse(e.getUsuario()))
                .build();
    }
}
