package br.com.fiap.webapp.resource;

import br.com.fiap.webapp.dto.request.AcessibilidadeRequest;
import br.com.fiap.webapp.dto.request.UsuarioRequest;
import br.com.fiap.webapp.dto.response.AcessibilidadeResponse;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Acessibilidade;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.service.AcessibilidadeService;
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
public class AcessibilidadeResource implements ResourceDTO<Acessibilidade, AcessibilidadeRequest, AcessibilidadeResponse> {

    @Autowired
    AcessibilidadeService service;

    @GetMapping
    public ResponseEntity<Collection<AcessibilidadeResponse>> findAll(

            @RequestParam(name = "legenda", required = false) String legenda,
            @RequestParam(name = "descricaoAudio", required = false) String descricaoAudio,
            @RequestParam(name = "tamanhoFonte", required = false) Integer tamanhoFonte,
            @RequestParam(name = "constraste", required = false) String constraste

    ) {
        var acessibilidade = Acessibilidade.builder()
                .legenda(legenda)
                .descricaoAudio(descricaoAudio)
                .tamanhoFonte(tamanhoFonte)
                .constraste(constraste)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Acessibilidade> example = Example.of(acessibilidade, matcher);
        Collection<Acessibilidade> Acessibilidades = service.findAll(example);
        var response = Acessibilidades.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<AcessibilidadeResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<AcessibilidadeResponse> save(@RequestBody @Valid AcessibilidadeRequest r) {
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
