package br.com.fiap.webapp.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email; // Importe esta classe

public record UsuarioRequest(

        @Size(min = 3, max = 50, message = "Nome inválido")
        @NotNull(message = "Nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotNull(message = "Email é obrigatório")
        String email,

        @Size(min = 6, max = 20, message = "Senha deve ter entre 6 e 20 caracteres")
        @NotNull(message = "Senha é obrigatória")
        String senha
) {

}

