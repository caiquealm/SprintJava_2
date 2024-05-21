package br.com.fiap.webapp.dto.response;


import lombok.Builder;
import java.time.Year;

@Builder
public record ProgressoResponse(

        Long id,


        String progressao,


        Integer pontuacao,

        UsuarioResponse usuario

) {

}
