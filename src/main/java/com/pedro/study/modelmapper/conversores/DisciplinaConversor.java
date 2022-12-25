package com.pedro.study.modelmapper.conversores;

import com.pedro.study.dto.input.DisciplinaIDTO;
import com.pedro.study.dto.output.DisciplinaODTO;
import com.pedro.study.model.Disciplina;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DisciplinaConversor {

    private ModelMapper modelMapper;

    public Disciplina iDTOToModel(DisciplinaIDTO dto){
       return  modelMapper.map(dto,Disciplina.class);
    }

    public DisciplinaODTO modelToODTO(Disciplina disciplina){ return  modelMapper.map(disciplina, DisciplinaODTO.class);
    }

}
