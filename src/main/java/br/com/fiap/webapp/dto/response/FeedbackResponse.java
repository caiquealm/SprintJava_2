package br.com.fiap.webapp.dto.response;



import lombok.Builder;



@Builder
public record FeedbackResponse(

        Long id,

        String textFeedback,

        String resposta,

        UsuarioResponse usuario

) {
}
