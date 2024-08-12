package telran.time;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Arrays;

public class PastTemporalDateProximity implements TemporalAdjuster {
    //TODO
    //some encapsulation
    //array of temporals supporting Day, Month, Year (Dates)
    private LocalDate[] localDates;
    public PastTemporalDateProximity(LocalDate[] localDates) {
        Arrays.sort(localDates);
        this.localDates = Arrays.copyOf(localDates, localDates.length);
    }

    @Override
    public Temporal adjustInto(Temporal temporal) {
    int index = Arrays.binarySearch(localDates, temporal);
    int pos = index < 0 ? -index - 2 : index--;
    return pos < localDates.length && pos >= 0 ? localDates[pos] : null;
    }

}
