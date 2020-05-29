package uk.co.sancode.learning_gradle.api;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.sancode.learning_gradle.builder.UserDtoBuilder;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class UserDtoTest {
    @Test
    public void givenDifferentType_equals_returnsFalse() {
        // Setup

        var sut = new UserDtoBuilder().build();

        // Exercise

        var actual = sut.equals("something");

        // Verify

        assertFalse(actual);
    }

    @Test
    @Parameters(method = "getUsers")
    public void equals_returnsCorrectValue(UserDto firstUser, UserDto secondUser, boolean expected) {
        // Setup

        // Exercise

        var actual = firstUser.equals(secondUser);

        // Verify

        assertEquals(expected, actual);
    }

    @Test
    public void givenObjectsEqual_hashCode_returnsSameValue() {
        // Setup

        var user1 = new UserDtoBuilder().build();
        var user2 = new UserDtoBuilder().withUserId(user1.getUserId()).withName(user1.getName()).withLastName(user1.getLastName()).withDateOfBirth(user1.getDateOfBirth()).build();

        // Exercise

        var hash1 = user1.hashCode();
        var hash2 = user2.hashCode();

        // Verify

        assertEquals(hash1, hash2);
    }

    @Test
    public void givenObjectsNotEqual_hashCode_returnsDifferentValue() {
        // Setup

        var user1 = new UserDtoBuilder().build();
        var user2 = new UserDtoBuilder().build();

        // Exercise

        var hash1 = user1.hashCode();
        var hash2 = user2.hashCode();

        // Verify

        assertNotEquals(hash1, hash2);
    }

    public Object[][] getUsers() {
        var uuid1 = UUID.randomUUID();
        var uuid2 = UUID.randomUUID();
        var date1 = LocalDate.of(2000, 1, 1);
        var date2 = LocalDate.of(2001, 1, 1);
        return new Object[][]{
                {new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date1).build(), new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date1).build(), true},
                {new UserDtoBuilder().withUserId(uuid2).withName("a").withLastName("b").withDateOfBirth(date1).build(), new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date1).build(), false},
                {new UserDtoBuilder().withUserId(uuid1).withName("b").withLastName("b").withDateOfBirth(date1).build(), new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date1).build(), false},
                {new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("c").withDateOfBirth(date1).build(), new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date1).build(), false},
                {new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date2).build(), new UserDtoBuilder().withUserId(uuid1).withName("a").withLastName("b").withDateOfBirth(date1).build(), false}
        };
    }
}
