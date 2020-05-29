package uk.co.sancode.learning_gradle.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.sancode.learning_gradle.api.UserDto;
import uk.co.sancode.learning_gradle.model.User;
import uk.co.sancode.learning_gradle.persistance.UserRepository;

import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository repository;
    private ModelMapper modelMapper;

    public UserController(@Autowired final UserRepository repository,
                          @Autowired final ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        Type type = new TypeToken<List<UserDto>>() {
        }.getType();

        return ResponseEntity.status(OK).body(modelMapper.map(repository.findAll(), type));
    }
}
