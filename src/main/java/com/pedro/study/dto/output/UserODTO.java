package com.pedro.study.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserODTO  {
    private Integer id;
    private String login;
    private String nome;
    private String senha;

    private List<AuthorizationODTO> authorizations;



}
