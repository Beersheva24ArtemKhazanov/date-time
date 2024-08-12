package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void localDateTest() {
        LocalDate current = LocalDate.now();
        LocalDateTime currentTime = LocalDateTime.now();
        ZonedDateTime currentZonedTime = ZonedDateTime.now();
        Instant currentInstant = Instant.now();
        LocalDate currentLocalTime = LocalDate.now();
        System.out.printf("Current date  is %s in ISO format \n", current);
        System.out.printf("Current date and time is %s in ISO format \n", currentTime);
        System.out.printf("Current  zoned date and time is %s in ISO format \n", currentZonedTime);
        System.out.printf("Current instant is %s in ISO format \n", currentInstant);
        System.out.printf("Current time is %s in ISO format \n", currentLocalTime);
        System.out.printf("Current time is %s in dd/mm/yyyy \n", currentLocalTime.format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy", Locale.forLanguageTag("he"))));
    }

    @Test
    void nextFriday13Test() {
        LocalDate current = LocalDate.of(2024, 8, 11);
        LocalDate expected = LocalDate.of(2024, 9, 13);
        TemporalAdjuster adjuster = new NextFriday13();
        assertEquals(expected, current.with(new NextFriday13()));
        assertThrows(Exception.class, () -> LocalTime.now().with(adjuster));
    }

    @Test 
    void pastTemporalDateProximityTest() {
        LocalDate date1 = LocalDate.of(2020, 8, 15);
        LocalDate date2 = LocalDate.of(2021, 9, 23);
        LocalDate date3 = LocalDate.of(2021, 11, 8);
        LocalDate date4 = LocalDate.of(2023, 4, 23);
        LocalDate exampleDate1 = LocalDate.of(2021, 10, 22);
        LocalDate exampleDate2 = LocalDate.of(2021, 11, 8);
        LocalDate exampleDate3 = LocalDate.of(2019, 11, 8);
        LocalDate exampleDate4 = LocalDate.of(2024, 5, 19);
        LocalDate exampleDate5 = LocalDate.of(2020, 8, 16);
        LocalDate[] localDates = {date1, date3, date4, date2};
        PastTemporalDateProximity adjuster = new PastTemporalDateProximity(localDates);
        assertEquals(date2, exampleDate1.with(adjuster));
        assertEquals(date3, exampleDate2.with(adjuster));
        assertEquals(null, exampleDate3.with(adjuster));
        assertEquals(date4, exampleDate4.with(adjuster));
        assertEquals(date1, exampleDate5.with(adjuster));
    }
}
