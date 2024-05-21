package br.com.fiap.webapp.service;

import br.com.fiap.webapp.dto.request.AcessibilidadeRequest;
import br.com.fiap.webapp.dto.response.AcessibilidadeResponse;
import br.com.fiap.webapp.entity.Acessibilidade;
import br.com.fiap.webapp.repository.AcessibilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcessibilidadeService implements  ServiceDTO<Acessibilidade, AcessibilidadeRequest, AcessibilidadeResponse>{

    @Autowired
    private AcessibilidadeRepository repo;
    @Override
    public Collection<Acessibilidade> findAll(Example<Acessibilidade> example) {
        return repo.findAll(example);
    }

    @Override
    public Acessibilidade findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Acessibilidade save(Acessibilidade e) {
        return repo.save(e);
    }

    @Override
    public Acessibilidade toEntity(AcessibilidadeRequest dto) {
        return Acessibilidade.builder()
                .legenda(dto.legenda())
                .constraste(dto.contraste())
                .tamanhoFonte(dto.tamanhoFonte())
                .descricaoAudio(dto.descricaoAudio())
                .build();
    }

    @Override
    public AcessibilidadeResponse toResponse(Acessibilidade e) {
        return AcessibilidadeResponse.builder()
                .id(e.getId())
                .legenda(e.getLegenda())
                .constraste(e.getConstraste())
                .descricaoAudio(e.getDescricaoAudio())
                .tamanhoFonte(e.getTamanhoFonte())
                .build();
    }
}