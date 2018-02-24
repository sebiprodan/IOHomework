package siit.homework;

import java.io.Serializable;

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


