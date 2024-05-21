package br.com.fiap.webapp.service;

import br.com.fiap.webapp.dto.request.LicaoRequest;
import br.com.fiap.webapp.dto.response.LicaoResponse;
import br.com.fiap.webapp.entity.Licao;
import br.com.fiap.webapp.repository.LicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LicaoService implements  ServiceDTO<Licao, LicaoRequest, LicaoResponse>{

    @Autowired
    private LicaoRepository repo;
    @Override
    public Collection<Licao> findAll(Example<Licao> example) {
        return repo.findAll(example);
    }

    @Override
    public Licao findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Licao save(Licao e) {
        return repo.save(e);
    }

    @Override
    public Licao toEntity(LicaoRequest dto) {
        return Licao.builder()
                .nomeLicao(dto.nomeLicao())
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .videoDemostracao(dto.videoDemostracao())
                .build();
    }

    @Override
    public LicaoResponse toResponse(Licao e) {
        return LicaoResponse.builder()
                .id(e.getId())
                .nomeLicao(e.getNomeLicao())
                .titulo(e.getTitulo())
                .descricao(e.getDescricao())
                .videoDemostracao(e.getVideoDemostracao())
                .build();
    }
}
