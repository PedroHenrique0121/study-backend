package com.pedro.study.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoLeiIDTO {

    @NotBlank
    private String descricao;
    @NotNull
    private Integer assuntoId;
}
