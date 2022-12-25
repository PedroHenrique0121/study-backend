package com.pedro.study.modelmapper;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.event.TableColumnModelListener;

@Configuration
public class ModelMapperConfig {


    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper;

    }

}
