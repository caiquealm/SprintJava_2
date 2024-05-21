package br.com.fiap.webapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Year;


public record LicaoRequest(

        @NotNull(message = "O campo lição nao pode ser nulo")
        @Size(min = 3, max = 10, message = "O campo lição deve ter entre 3 à 10 caracteres")
        String nomeLicao,


        @NotNull(message = "O campo titulo nao pode ser nulo")
        @Size(min = 3, max = 10, message = "O campo titulo deve ter entre 3 à 10 caracteres")
        String titulo,


        @NotNull(message = "O campo descricao nao pode ser nulo")
        @Size(min = 3, max = 25, message = "O campo descricao deve ter entre 3 à 25 caracteres")
        String descricao,


        String videoDemostracao,

        AbstractRequest Instrumento

) {

}

