package com.pedro.study.dto.output;

import com.pedro.study.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AuthorizationODTO {


    private Integer id;
    private RoleODTO role;
    private Integer idUser;

//    public void setRoleDescricao(String[] roleDescricao) {
//        for (int i = 0; i < roleDescricao.length; i++) {
//            this.roleDescricao[i] = roleDescricao[i];
//        }
//    }
}
