package com.pedro.study.dto.input;


import com.pedro.study.model.Assunto;
import com.pedro.study.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Data
public class DisciplinaIDTO {

    @NotBlank
    private String descricao;
    @NotNull
    private Integer userId;
}
