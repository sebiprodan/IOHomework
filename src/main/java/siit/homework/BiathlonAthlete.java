package siit.homework;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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

    public static void addAthleteInput() {
        SkiBiathlon.allAthletes = new LinkedHashSet<>();

        SkiBiathlon.allAthletes.add(new BiathlonAthlete(1, "Sebastian Prodan", "RO",
                new SkiTimeResult(30, 24), "xxxox", "ooxxx", "xxxxx"));
        SkiBiathlon.allAthletes.add(new BiathlonAthlete(2, "Bianca Prodan", "RO",
                new SkiTimeResult(29, 30), "xxxxx", "xoxxx", "xxoxx"));


        try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonResults"))) {
            System.setOut(out);
            for (BiathlonAthlete athletes : SkiBiathlon.allAthletes) {
                System.out.println(athletes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
