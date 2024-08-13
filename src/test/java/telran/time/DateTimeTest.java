package telran.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.*;
import java.time.chrono.MinguoDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
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

     Temporal[] temporals = {
            MinguoDate.now().minus(1, ChronoUnit.DAYS),
            ThaiBuddhistDate.now().plus(3, ChronoUnit.DAYS),
            LocalDate.now().minus(2, ChronoUnit.YEARS),
            LocalDateTime.now().plus(1, ChronoUnit.MONTHS)
    };
    PastTemporalDateProximity adjuster = new PastTemporalDateProximity(temporals);

@Test
void localDateTimeAsMinguoDate(){
    LocalDateTime ldt = LocalDateTime.now();
    LocalDateTime expected = LocalDateTime.now().minusDays(1);
    assertEquals(0, ChronoUnit.SECONDS.between(ldt.with(adjuster), expected));
}
@Test
void minguoDateAsZonedDateTime() {
    MinguoDate md = MinguoDate.now().plus(2, ChronoUnit.MONTHS);
    MinguoDate expected = MinguoDate.now().plus(1, ChronoUnit.MONTHS);
    assertEquals(expected, md.with(adjuster));
}
@Test
void zonedDateTimeAsThaiBuddhist() {
    Temporal zdt = ZonedDateTime.now().plusDays(4);
    ZonedDateTime expected = ZonedDateTime.now().plus(3, ChronoUnit.DAYS);
    assertEquals(0, ChronoUnit.SECONDS.between(zdt.with(adjuster), expected));
}
@Test
void localDateNotFound() {
    LocalDate ld = LocalDate.now().minusYears(3);
    assertNull(ld.with(adjuster));
}
}
