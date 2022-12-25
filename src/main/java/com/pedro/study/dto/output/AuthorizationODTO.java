package com.pedro.study.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthorizationODTO {

    private Integer id;
    private RoleODTO role;
    private Integer idUser;


}
