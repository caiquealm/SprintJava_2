package br.com.fiap.webapp.dto.response;


import br.com.fiap.webapp.entity.Usuario;
import lombok.Builder;
import java.time.Year;

@Builder
public record UsuarioResponse(

        Long id,

        String nome,

        String email,

        String senha

) {

}

