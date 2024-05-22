package br.com.fiap.webapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_FEEDBACK")
public class Feedback {
    @Id
    @GeneratedValue(generator = "SQ_FEEDBACK",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_FEEDBACK",sequenceName = "SQ_FEEDBACK",allocationSize = 1)
    @Column(name = "ID_FEEDBACk")
    private Long id;

    private String textFeedback;

    private String resposta;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "TB_FEEDBACK_USUARIO",
            joinColumns = {
                    @JoinColumn(
                            name = "FEEDBACK",
                            referencedColumnName = "ID_FEEDBACK",
                            foreignKey = @ForeignKey(name = "FK_FEEDBACK_USUARIO")
                    )
            }, inverseJoinColumns = {
            @JoinColumn(
                    name = "USUARIO",
                    referencedColumnName = "ID_USUARIO",
                    foreignKey = @ForeignKey(name = "FK_USUARIO_FEEDBACK")
            )
    }
    )
    private Usuario usuario;

}
