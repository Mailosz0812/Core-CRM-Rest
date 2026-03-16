package org.mailosz.crmrest.config;

import org.modelmapper.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        Converter<UUID, String> uuidToString = ctx ->
                ctx.getSource() == null ? null : ctx.getSource().toString();

        modelMapper.addConverter(uuidToString);
        return modelMapper;

    }
}
