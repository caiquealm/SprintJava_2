package br.com.fiap.webapp.service;

import br.com.fiap.webapp.dto.request.UsuarioRequest;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse>{

    @Autowired
    private UsuarioRepository repo;



    @Override
    public Collection<Usuario> findAll(Example<Usuario> example) {
        return repo.findAll(example);
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario e) {
        return repo.save(e);
    }

    @Override
    public Usuario toEntity(UsuarioRequest dto) {
        return Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {
        return UsuarioResponse.builder()
                .id(e.getId())
                .nome(e.getNome())
                .email(e.getEmail())
                .senha(e.getSenha())
                .build();
    }
}
