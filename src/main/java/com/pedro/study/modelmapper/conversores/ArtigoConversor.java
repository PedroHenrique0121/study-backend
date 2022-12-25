package com.pedro.study.modelmapper.conversores;


import com.pedro.study.dto.input.ArtigoIDTO;
import com.pedro.study.dto.output.ArtigoODTO;
import com.pedro.study.model.Artigo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ArtigoConversor {

    private ModelMapper modelMapper;

    public Artigo iDTOToModel(ArtigoIDTO dto) {
        return modelMapper.map(dto, Artigo.class);
    }

    public ArtigoODTO modelToODTO(Artigo artigo) {
        return modelMapper.map(artigo, ArtigoODTO.class);
    }

}
