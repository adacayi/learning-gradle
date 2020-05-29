package uk.co.sancode.learning_gradle.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

public class UserDto {
    @NotNull
    private UUID userId;

    @NotNull
    @Pattern(regexp = "[A-Za-z\\s]{3,75}")
    private String name;

    @NotNull
    @Pattern(regexp = "[A-Za-z\\s]{3,75}")
    private String lastName;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    public UserDto(final UUID userId, final String name, final String lastName, final LocalDate dateOfBirth) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.userId)
                .append(this.name)
                .append(this.lastName)
                .append(this.dateOfBirth)
                .build();
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof UserDto)) {
            return false;
        }

        var other = (UserDto) obj;
        return new EqualsBuilder()
                .append(this.userId, other.userId)
                .append(this.name, other.name)
                .append(this.lastName, other.lastName)
                .append(this.dateOfBirth, other.dateOfBirth)
                .build();
    }
}
