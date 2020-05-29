package uk.co.sancode.learning_gradle.builder;

import org.apache.commons.lang3.RandomStringUtils;
import uk.co.sancode.learning_gradle.api.UserDto;

import java.time.LocalDate;
import java.util.UUID;

import static uk.co.sancode.learning_gradle.utilities.RandomUtilities.getRandomInt;

public class UserDtoBuilder {
    private UUID userId;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;

    public UserDtoBuilder() {
        userId = UUID.randomUUID();
        name = RandomStringUtils.randomAlphabetic(getRandomInt(5, 20));
        lastName = RandomStringUtils.randomAlphabetic(getRandomInt(5, 20));
        dateOfBirth = LocalDate.now().minusYears(getRandomInt(10, 60)).minusDays(getRandomInt(0, 365));
    }

    public UserDtoBuilder withUserId(UUID userId) {
        this.userId = userId;
        return this;
    }

    public UserDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDtoBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserDto build() {
        return new UserDto(userId, name, lastName, dateOfBirth);
    }
}
