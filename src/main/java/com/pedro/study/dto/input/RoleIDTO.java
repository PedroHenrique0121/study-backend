package com.pedro.study.dto.input;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleIDTO {

    @NotBlank
    private String descricao;

}
