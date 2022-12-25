package com.pedro.study.dto.input;

import com.pedro.study.dto.output.PenaODTO;
import com.pedro.study.model.Artigo;
import com.pedro.study.model.Pena;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PenaIDTO {

    @NotBlank
    private String descricao;
    @NotBlank
    private String categoria;
    @NotNull
    private Integer artigoId;

}
