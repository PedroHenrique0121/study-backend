package com.pedro.study.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String login;
    @NotBlank
    private String senha;
}
