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
public class Artigo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true,length = 2000)
    private String descricao;
    private String numero;
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicoLei_id")
    private TopicoLei topicoLei;

    @OneToMany(mappedBy = "artigo",fetch = FetchType.LAZY)
    private List<Pena> penas;


}
