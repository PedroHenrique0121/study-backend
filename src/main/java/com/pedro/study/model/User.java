package com.pedro.study.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank
    private String nome;

    @Column(unique = true)
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Disciplina> disciplinas;
    @OneToMany(mappedBy = "user")
    private List<Authorization> authorizations;

}
