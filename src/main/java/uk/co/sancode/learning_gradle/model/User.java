package uk.co.sancode.learning_gradle.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID userId;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;

    private User() {

    }

    public User(final UUID userId, final String name, final String lastName, final LocalDate dateOfBirth) {
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
        if (!(obj instanceof User)) {
            return false;
        }

        var other = (User) obj;
        return new EqualsBuilder()
                .append(this.userId, other.userId)
                .append(this.name, other.name)
                .append(this.lastName, other.lastName)
                .append(this.dateOfBirth, other.dateOfBirth)
                .build();
    }
}
