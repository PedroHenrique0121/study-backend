package com.pedro.study.modelmapper.conversores;


import com.pedro.study.dto.input.UserIDTO;
import com.pedro.study.dto.output.UserODTO;
import com.pedro.study.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConversor {

    private ModelMapper modelMapper;

    public User dTOToUser(UserIDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserODTO modelToODTO(User user) {
        return modelMapper.map(user, UserODTO.class);
    }


}
