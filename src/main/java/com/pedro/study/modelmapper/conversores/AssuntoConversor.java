package com.pedro.study.modelmapper.conversores;

import com.pedro.study.dto.input.AssuntoIDTO;
import com.pedro.study.dto.output.AssuntoODTO;
import com.pedro.study.model.Assunto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AssuntoConversor {

    private ModelMapper modelMapper;

    public Assunto iDTOToModel(AssuntoIDTO dto) {
        return modelMapper.map(dto, Assunto.class);
    }

    public AssuntoODTO modelToODTO(Assunto assunto) {
        return modelMapper.map(assunto, AssuntoODTO.class);
    }


}
