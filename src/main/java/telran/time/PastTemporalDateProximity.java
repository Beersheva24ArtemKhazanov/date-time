package telran.time;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class PastTemporalDateProximity implements TemporalAdjuster {
    //TODO
    //some encapsulation
    //array of temporals supporting Day, Month, Year (Dates)
    private Temporal[] dates;
    public PastTemporalDateProximity(Temporal[] dates) {
        Arrays.sort(dates);
        this.dates = Arrays.copyOf(dates, dates.length);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
    int index = Arrays.binarySearch(dates, temporal);
    int pos = index < 0 ? -index - 2 : index--;
    return pos < dates.length && pos >= 0 ? dates[pos] : null;
    }

}
