package br.com.fiap.webapp.dto.response;


import br.com.fiap.webapp.entity.Instrumento;
import lombok.Builder;

import java.util.Set;

@Builder
public record LicaoResponse(

        Long id,


        String nomeLicao,


        String titulo,


        String descricao,


        String videoDemostracao,

        Set<Instrumento> instrumentos
){

}
