package com.pedro.study.dto.output;

import com.pedro.study.model.Artigo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PenaODTO {

    private Integer id;
    private String descricao;
    private String categoria;
    private String descricaoArtigo;
    private String numeroArtigo;

}
