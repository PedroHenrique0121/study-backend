package com.pedro.study.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplina_id")

    private Disciplina disciplina;

    @OneToMany(mappedBy = "assunto",fetch = FetchType.LAZY)
    private List<TopicoLei> topicosLeis;

}
