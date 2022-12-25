package com.pedro.study.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuntoODTO {

    private Integer id;
    private String descricao;
    private DisciplinaODTO disciplina;

}
