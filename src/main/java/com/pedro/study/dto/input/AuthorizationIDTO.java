package com.pedro.study.dto.input;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationIDTO {
    private Integer id;
    @NotNull
    private  Integer idRole;
    @NotNull
    private Integer idUser;

}
