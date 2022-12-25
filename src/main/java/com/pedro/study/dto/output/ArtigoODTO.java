package com.pedro.study.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtigoODTO {

    private Integer id;
    private String descricao;
    private String numero;
    private String categoria;
    private TopicoLeiODTO topicoLei;
    private List<PenaODTO> penas;
}
