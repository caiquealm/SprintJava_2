package br.com.fiap.webapp.resource;

import br.com.fiap.webapp.dto.request.FeedbackRequest;
import br.com.fiap.webapp.dto.response.FeedbackResponse;
import br.com.fiap.webapp.entity.Feedback;
import br.com.fiap.webapp.service.FeedbackService;
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
public class FeedbackResource implements ResourceDTO<Feedback, FeedbackRequest, FeedbackResponse> {

    @Autowired
    FeedbackService service;

    @GetMapping
    public ResponseEntity<Collection<FeedbackResponse>> findAll(

            @RequestParam(name = "textFeedback", required = false) String textFeedback,
            @RequestParam(name = "resposta", required = false) String resposta
    ) {
        var feedback = Feedback.builder()
                .textFeedback(textFeedback)
                .resposta(resposta)
                .build();

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withIgnoreNullValues()
                .withIgnoreCase();

        Example<Feedback> example = Example.of(feedback, matcher);
        Collection<Feedback> Feedbacks = service.findAll(example);
        var response = Feedbacks.stream().map(service::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<FeedbackResponse> findById(@PathVariable Long id) {
        var entity = service.findById(id);
        if (Objects.isNull(entity)) return ResponseEntity.notFound().build();
        var response = service.toResponse(entity);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PostMapping
    @Override
    public ResponseEntity<FeedbackResponse> save(@RequestBody @Valid FeedbackRequest r) {
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
