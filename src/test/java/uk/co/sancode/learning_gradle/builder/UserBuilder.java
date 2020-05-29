package uk.co.sancode.learning_gradle.builder;

import org.apache.commons.lang3.RandomStringUtils;
import uk.co.sancode.learning_gradle.model.User;

import java.time.LocalDate;
import java.util.UUID;

import static uk.co.sancode.learning_gradle.utilities.RandomUtilities.getRandomInt;

public class UserBuilder {
    private UUID userId;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;

    public UserBuilder() {
        userId = UUID.randomUUID();
        name = RandomStringUtils.randomAlphabetic(getRandomInt(5, 20));
        lastName = RandomStringUtils.randomAlphabetic(getRandomInt(5, 20));
        dateOfBirth = LocalDate.now().minusYears(getRandomInt(10, 60)).minusDays(getRandomInt(0, 365));
    }

    public UserBuilder withUserId(UUID id) {
        this.userId = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public User build() {
        return new User(userId, name, lastName, dateOfBirth);
    }
}
