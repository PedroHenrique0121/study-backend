package com.pedro.study.dto.input;

import com.pedro.study.model.Disciplina;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuntoIDTO {
    @NotBlank
    private String descricao;
    @NotNull
    private Integer disciplinaId;

}
