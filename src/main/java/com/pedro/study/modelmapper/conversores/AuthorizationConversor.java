package com.pedro.study.modelmapper.conversores;

import com.pedro.study.dto.input.AuthorizationIDTO;
import com.pedro.study.dto.output.AuthorizationODTO;
import com.pedro.study.model.Authorization;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AuthorizationConversor {
    private ModelMapper modelMapper;

    public Authorization dtoToModel(AuthorizationIDTO dto){
        return modelMapper.map(dto,Authorization.class);
    }

    public AuthorizationODTO modelToDTO(Authorization model){
        return modelMapper.map(model, AuthorizationODTO.class);
    }

    public List<AuthorizationODTO> listModelToListDTO(List<Authorization> lista){
        return lista.stream().map(this::modelToDTO)
                .collect(Collectors.toList());
    }
    public Page<AuthorizationODTO> pageModelToPageDTO(Page<Authorization> page){
        return page.map(this::modelToDTO);
    }
}
