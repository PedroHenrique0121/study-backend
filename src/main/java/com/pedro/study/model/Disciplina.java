package com.pedro.study.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descricao;
    @OneToMany(mappedBy = "disciplina",fetch = FetchType.LAZY )
    private List<Assunto>  assuntos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


}
