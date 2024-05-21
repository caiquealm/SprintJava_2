package br.com.fiap.webapp.dto.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record AcessibilidadeRequest(

        @NotNull(message = "Legenda não pode ser nula")
        @Size(min = 1, max = 255, message = "Legenda deve ter entre 1 e 255 caracteres")
        String legenda,

        @NotNull(message = "Descrição de áudio não pode ser nula")
        @Size(min = 1, max = 255, message = "Descrição de áudio deve ter entre 1 e 255 caracteres")
        String descricaoAudio,

        @NotNull(message = "Contraste não pode ser nulo")
        @Size(min = 1, max = 100, message = "Contraste deve ter entre 1 e 100 caracteres")
        String contraste,

        @NotNull(message = "Tamanho da fonte não pode ser nulo")
        @Min(value = 8, message = "Tamanho da fonte deve ser no mínimo 8")
        @Max(value = 72, message = "Tamanho da fonte deve ser no máximo 72")
        Integer tamanhoFonte
){

}
