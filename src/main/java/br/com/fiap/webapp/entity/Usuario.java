package br.com.fiap.webapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(generator = "SQ_USUARIO", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_USUARIO", sequenceName = "SQ_USUARIO",allocationSize = 1)
    private Long id;

    private String nome;

    private String email;

    private String senha;

}
