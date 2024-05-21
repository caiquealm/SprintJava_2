package br.com.fiap.webapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AbstractRequest(

        @Positive(message = "Id deve ser um número positivo")
        @NotNull(message = "Id deve ser informado")
        Long id
) {

}


