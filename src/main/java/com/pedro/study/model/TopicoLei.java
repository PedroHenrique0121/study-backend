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
public class TopicoLei {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assunto_id")
    private Assunto assunto;

    @OneToMany(mappedBy = "topicoLei",fetch = FetchType.LAZY)
    private List<Artigo> artigos;



}
