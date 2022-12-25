package com.pedro.study.modelmapper.conversores;


import com.pedro.study.dto.input.PenaIDTO;
import com.pedro.study.dto.output.PenaODTO;
import com.pedro.study.model.Pena;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PenaConversor {

    private ModelMapper modelMapper;

    public Pena iDTOToModel(PenaIDTO dto){ return modelMapper.map(dto, Pena.class); }
    public PenaODTO modelToODTO(Pena pena){ return modelMapper.map(pena,PenaODTO.class);}
}
