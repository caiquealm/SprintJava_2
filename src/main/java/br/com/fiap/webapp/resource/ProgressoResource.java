package br.com.fiap.webapp.resource;

import br.com.fiap.webapp.dto.request.InstrumentoRequest;
import br.com.fiap.webapp.dto.request.ProgressoRequest;
import br.com.fiap.webapp.dto.request.UsuarioRequest;
import br.com.fiap.webapp.dto.response.InstrumentoResponse;
import br.com.fiap.webapp.dto.response.ProgressoResponse;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Instrumento;
import br.com.fiap.webapp.entity.Progresso;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.service.InstrumentoService;
import br.com.fiap.webapp.service.ProgressoService;
import br.com.fiap.webapp.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping(value = "/progresso")
@RequiredArgsConstructor
public class ProgressoResource implements ResourceDTO<Progresso, ProgressoRequest, ProgressoResponse>{
    @Autowired
    ProgressoService service;

    @GetMapping
    public ResponseEntity<Collection<ProgressoResponse>> findAll(

            @RequestParam(name = "progressao", required = false) String progressao,
            @RequestParam(name = "pontuacao", required = false) Integer pontuacao,
            @RequestParam(name = "usuario.nome", required = false) String nomeUsuario

    ) {
        var progresso = Progresso.builder()
                .progressao(progressao)
                .pontuacao(pontuacao)
                .build();

        var usuario = Usuario.builder()
                .nome(nomeUsuario)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Progresso> example = Example.of(progresso, matcher);
        Collection<Progresso> Progressos = service.findAll(example);
        var response = Progressos.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<ProgressoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<ProgressoResponse> save(@RequestBody @Valid ProgressoRequest r) {
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
