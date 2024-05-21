package br.com.fiap.webapp.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record InstrumentoRequest(

        @NotNull(message = "O campo instrumento não pode ser nulo")
        @Size(min = 1, max = 15, message = "Campo deve ter entre 1 à 15 caracteres")
        String nomeInstrumento,

        @NotNull(message = "O campo categoria não pode ser nulo")
        @Size(min = 1, max = 10, message = "Campo deve ter entre 1 à 10 caracteres")
        String categoria,
        @NotNull(message = "O campo dificuldade não pode ser nulo")
        @Size(min = 1, max = 10, message = "Campo deve ter entre 1 à 10 caracteres")
        String dificuldade

) {

}
