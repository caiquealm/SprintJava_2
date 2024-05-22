package br.com.fiap.webapp.resource;

import br.com.fiap.webapp.dto.request.UsuarioRequest;
import br.com.fiap.webapp.dto.response.UsuarioResponse;
import br.com.fiap.webapp.entity.Usuario;
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
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
public class UsuarioResource implements ResourceDTO<Usuario, UsuarioRequest, UsuarioResponse> {

    @Autowired
    UsuarioService service;

    @GetMapping
    public ResponseEntity<Collection<UsuarioResponse>> findAll(

            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "senha", required = false) String senha

    ) {
        var usuario = Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Usuario> example = Example.of(usuario, matcher);
        Collection<Usuario> Usuarios = service.findAll(example);
        var response = Usuarios.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<UsuarioResponse> save(@RequestBody @Valid UsuarioRequest r) {
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
