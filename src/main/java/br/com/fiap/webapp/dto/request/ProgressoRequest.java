package br.com.fiap.webapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProgressoRequest(

        @Size(min = 1, max = 255, message = "Progresso deve ter entre 1 e 255 caracteres")
        @NotNull(message = "Progresso é obrigatório")
        String progressao,

        @Positive(message = "Pontuação deve ser um número positivo")
        @NotNull(message = "Pontuação é obrigatória")
        Integer pontuacao,

        @NotNull(message = "Usuário é obrigatório")
        AbstractRequest usuario
){

}
