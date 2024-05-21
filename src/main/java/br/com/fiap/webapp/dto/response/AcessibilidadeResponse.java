package br.com.fiap.webapp.dto.response;


import lombok.Builder;
@Builder


public record AcessibilidadeResponse(

        Long id,

        String legenda,

        String descricaoAudio,

        String constraste,

        Integer tamanhoFonte

) {

}
