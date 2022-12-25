package com.pedro.study.modelmapper.conversores;


import com.pedro.study.dto.input.RoleIDTO;
import com.pedro.study.dto.output.RoleODTO;
import com.pedro.study.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleConversor {
    private ModelMapper modelMapper;

    public Role dtoToModel(RoleIDTO dto){
        return modelMapper.map(dto,Role.class);
    }

    public RoleODTO modelToDTO(Role model){
        return modelMapper.map(model, RoleODTO.class);
    }

}
