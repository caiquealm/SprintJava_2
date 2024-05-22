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
@Table(name = "TB_ACESSIBILIDADE")
public class Acessibilidade {
    @Id
    @Column(name = "ID_ACESSIBILIDADE")
    @GeneratedValue(generator = "SQ_ACESSIBILIDADE",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_ACESSIBILIDADE",sequenceName = "SQ_ACESSIBILIDADE",allocationSize = 1)
    private Long id;

    private String legenda;

    private String descricaoAudio;

    private String constraste;

    private Integer tamanhoFonte;
}
