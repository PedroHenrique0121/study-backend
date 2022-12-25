package com.pedro.study.dto.output;

import com.pedro.study.model.Artigo;
import com.pedro.study.model.Assunto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicoLeiODTO {

    private Integer id;
    private String descricao;
    private AssuntoODTO assunto;

}
