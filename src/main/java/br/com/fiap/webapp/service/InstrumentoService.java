package br.com.fiap.webapp.service;

import br.com.fiap.webapp.dto.request.InstrumentoRequest;
import br.com.fiap.webapp.dto.response.InstrumentoResponse;
import br.com.fiap.webapp.entity.Instrumento;
import br.com.fiap.webapp.repository.InstrumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class InstrumentoService implements  ServiceDTO<Instrumento, InstrumentoRequest, InstrumentoResponse>{

    @Autowired
    private InstrumentoRepository repo;
    @Override
    public Collection<Instrumento> findAll(Example<Instrumento> example) {
        return repo.findAll(example);
    }

    @Override
    public Instrumento findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Instrumento save(Instrumento e) {
        return repo.save(e);
    }

    @Override
    public Instrumento toEntity(InstrumentoRequest dto) {
        return Instrumento.builder()
                .nomeInstrumento(dto.nomeInstrumento())
                .categoria(dto.categoria())
                .dificuldade(dto.dificuldade())
                .build();
    }

    @Override
    public InstrumentoResponse toResponse(Instrumento e) {
        return InstrumentoResponse.builder()
                .id(e.getId())
                .nomeInstrumento(e.getNomeInstrumento())
                .categoria(e.getCategoria())
                .dificuldade(e.getDificuldade())
                .build();
    }
}
