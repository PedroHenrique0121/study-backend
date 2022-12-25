package com.pedro.study.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Problema {


    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private String path;
    private List<Campo> campos;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Campo {
        private String nome;
        private String mensagem;

    }
}
