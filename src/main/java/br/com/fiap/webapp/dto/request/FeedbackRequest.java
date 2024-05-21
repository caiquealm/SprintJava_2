package br.com.fiap.webapp.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record FeedbackRequest(

        @NotNull(message = "Feedback não pode ser nula")
        @Size(min = 5, max = 255,message = "O feedback deve conter no mínimo 5 caracteres")
        String textFeedback,

        @Size(min = 5, max = 255,message = "A resposta deve conter no mínimo 5 caracteres")
        String resposta,

        @NotNull(message = "Usuário é obrigatório")
        AbstractRequest usuario
) {

}
