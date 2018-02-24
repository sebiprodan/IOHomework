package siit.homework;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class BiathlonAthlete implements Serializable {

    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    protected SkiTimeResult skiTimeResult;
    private String firstShootingRange;
    private String secondShootingRange;
    private String thirdShootingRange;

    public BiathlonAthlete(int athleteNumber, String athleteName, String countryCode, SkiTimeResult skiTimeResult,
                           String firstShootingRange, String secondShootingRange, String thirdShootingRange) {

        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.firstShootingRange = firstShootingRange;
        this.secondShootingRange = secondShootingRange;
        this.thirdShootingRange = thirdShootingRange;
    }

    @Override
    public String toString() {
        return athleteNumber + "," + athleteName + "," + countryCode + "," + skiTimeResult +
                "," + firstShootingRange + "," + secondShootingRange + "," + thirdShootingRange;
    }

}


