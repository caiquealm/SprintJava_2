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
@Table(name = "TB_INSTRUMENTO")
public class Instrumento {
    @Id
    @GeneratedValue(generator = "SQ_INSTRUMENTO",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_INSTRUMENTO",sequenceName = "SQ_INSTRUMENTO",allocationSize = 1)
    private Long id;

    private String nomeInstrumento;

    private String categoria;

    private String dificuldade;
}
