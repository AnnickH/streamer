package fr.aelion.streamer.components;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean //class qui se charge toute seule
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
