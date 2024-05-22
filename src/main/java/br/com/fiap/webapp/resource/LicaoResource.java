package br.com.fiap.webapp.resource;

import br.com.fiap.webapp.dto.request.InstrumentoRequest;
import br.com.fiap.webapp.dto.request.LicaoRequest;
import br.com.fiap.webapp.dto.request.UsuarioRequest;
import br.com.fiap.webapp.dto.response.InstrumentoResponse;
import br.com.fiap.webapp.dto.response.LicaoResponse;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Instrumento;
import br.com.fiap.webapp.entity.Licao;
import br.com.fiap.webapp.entity.Usuario;
import br.com.fiap.webapp.service.InstrumentoService;
import br.com.fiap.webapp.service.LicaoService;
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
import java.util.Set;

@RestController
@RequestMapping(value = "/licao")
@RequiredArgsConstructor
public class LicaoResource implements ResourceDTO<Licao, LicaoRequest, LicaoResponse>{
    @Autowired
    LicaoService service;

    @GetMapping
    public ResponseEntity<Collection<LicaoResponse>> findAll(

            @RequestParam(name = "nomeLicao", required = false) String nomeLicao,
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "descricao", required = false) String descricao ,
            @RequestParam(name = "videoDemonstracao", required = false) String dificuldade,
            @RequestParam(name = "instrumento.nomeInstrumento", required = false) String nomeInstrumento

    ) {
        var licao = Licao.builder()
                .nomeLicao(nomeLicao)
                .titulo(titulo)
                .descricao(descricao)
                .videoDemostracao(dificuldade)
                .build();

        var instrumento = Instrumento.builder()
                .nomeInstrumento(nomeInstrumento)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Licao> example = Example.of(licao, matcher);
        Collection<Licao> Licoes = service.findAll(example);
        var response = Licoes.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<LicaoResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<LicaoResponse> save(@RequestBody @Valid LicaoRequest r) {
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