package com.pedro.study.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinaODTO {

    private Integer id;
    private String descricao;
    private Integer userId;

}
