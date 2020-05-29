package uk.co.sancode.learning_gradle.utilities;

import java.time.LocalDate;

public class RandomUtilities {
    public static int getRandomInt(int startInclusive, int endInclusive) {
        return (int) (Math.random() * (endInclusive - startInclusive + 1)) + startInclusive;
    }

    public static LocalDate getRandomDate(int beforeDayInclusive, int afterDayInclusive) {
        var daysToAdd = getRandomInt(-beforeDayInclusive, afterDayInclusive);
        return LocalDate.now().minusDays(daysToAdd);
    }
}
