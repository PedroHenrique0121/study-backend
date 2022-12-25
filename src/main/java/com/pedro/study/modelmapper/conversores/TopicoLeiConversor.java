package com.pedro.study.modelmapper.conversores;
import com.pedro.study.dto.input.TopicoLeiIDTO;
import com.pedro.study.dto.output.TopicoLeiODTO;
import com.pedro.study.model.TopicoLei;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TopicoLeiConversor {

    private ModelMapper modelMapper;

    public TopicoLeiODTO modelToODTO(TopicoLei topicoLei){ return modelMapper.map(topicoLei,TopicoLeiODTO.class); }

    public TopicoLei iDTOToModel(TopicoLeiIDTO dto){ return modelMapper.map(dto,TopicoLei.class); }


}
