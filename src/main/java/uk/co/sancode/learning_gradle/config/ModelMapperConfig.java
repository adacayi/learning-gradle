package uk.co.sancode.learning_gradle.config;

import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.sancode.learning_gradle.api.UserDto;
import uk.co.sancode.learning_gradle.model.User;

import java.time.LocalDate;
import java.util.UUID;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);

        Provider<UserDto> userDtoProvider = new AbstractProvider<>() {
            public UserDto get() {
                return new UserDto(UUID.randomUUID(), "", "", LocalDate.MIN);
            }
        };

        modelMapper.createTypeMap(User.class, UserDto.class).setProvider(userDtoProvider);

        return modelMapper;
    }
}
