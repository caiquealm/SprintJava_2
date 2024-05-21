package br.com.fiap.webapp.dto.response;

import lombok.Builder;

import java.util.Set;

@Builder
public record InstrumentoResponse(

        Long id,

        String nomeInstrumento,

        String categoria,

        String dificuldade
) {

}

