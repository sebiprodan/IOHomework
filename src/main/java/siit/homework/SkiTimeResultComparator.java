package siit.homework;

import java.util.Comparator;

public class SkiTimeResultComparator implements Comparator {

    public int compare(Object o1, Object o2) {

        BiathlonAthlete ba1 = (BiathlonAthlete) o1;
        BiathlonAthlete ba2 = (BiathlonAthlete) o2;

        if (ba1.skiTimeResult == ba2.skiTimeResult)
            return 0;
      //  else if (ba1.skiTimeResult > ba2.skiTimeResult)
        //    return 1;
        else
            return -1;
    }
}

