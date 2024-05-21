package br.com.fiap.webapp.resource;

import br.com.fiap.webapp.dto.request.InstrumentoRequest;
import br.com.fiap.webapp.dto.request.UsuarioRequest;
import br.com.fiap.webapp.dto.response.InstrumentoResponse;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Instrumento;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.service.InstrumentoService;
import br.com.fiap.webapp.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@Service
public class InstrumentoResource implements ResourceDTO<Instrumento, InstrumentoRequest, InstrumentoResponse>{
    @Autowired
    InstrumentoService service;

    @GetMapping
    public ResponseEntity<Collection<InstrumentoResponse>> findAll(

            @RequestParam(name = "nomeInstrumento", required = false) String nomeInstrumento,
            @RequestParam(name = "dificuldade", required = false) String dificuldade,
            @RequestParam(name = "categoria", required = false) String categoria

    ) {
        var instrumento = Instrumento.builder()
                .nomeInstrumento(nomeInstrumento)
                .dificuldade(dificuldade)
                .categoria(categoria)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Instrumento> example = Example.of(instrumento, matcher);
        Collection<Instrumento> Instrumentos = service.findAll(example);
        var response = Instrumentos.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<InstrumentoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<InstrumentoResponse> save(@RequestBody @Valid InstrumentoRequest r) {
        var entity = service.toEntity(r);
        var saved = service.save(entity);
        var response = service.toResponse(saved);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
