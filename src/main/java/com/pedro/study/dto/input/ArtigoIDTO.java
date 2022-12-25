package com.pedro.study.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtigoIDTO {

    @NotBlank
    private String descricao;
    @NotBlank
    private String numero;
    @NotBlank
    private String categoria;
    @NotNull
    private Integer topicoLeiId;
}
