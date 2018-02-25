package siit.homework;

import java.util.Comparator;

public class SkiTimeResultComparator implements Comparator <BiathlonAthlete>{

    @Override
    public int compare(BiathlonAthlete e1, BiathlonAthlete e2) {

        return e1.standingCalculation().compareTo(e2.standingCalculation());
    }
}



