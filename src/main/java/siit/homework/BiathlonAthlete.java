package siit.homework;

import java.io.*;
import java.util.*;
import java.util.TreeSet;

public class BiathlonAthlete implements Serializable {

    private int athleteNumber;
    private String athleteName;
    private String countryCode;
    private String skiTimeResult;
    private int skiTimeResultInSeconds;
    protected int finalSkiTimeResultInSeconds;
    private String firstShootingRange;
    private String secondShootingRange;
    private String thirdShootingRange;
    protected int penalties;
    protected String finalReconvertedTime;

    public BiathlonAthlete(int athleteNumber, String athleteName, String countryCode, String skiTimeResult,
                           String firstShootingRange, String secondShootingRange, String thirdShootingRange) {

        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResult = skiTimeResult;
        this.firstShootingRange = firstShootingRange;
        this.secondShootingRange = secondShootingRange;
        this.thirdShootingRange = thirdShootingRange;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public void setAthleteNumber(int athleteNumber) {
        this.athleteNumber = athleteNumber;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSkiTimeResult() {
        return skiTimeResult;
    }

    public void setSkiTimeResult(String skiTimeResult) {
        this.skiTimeResult = skiTimeResult;
    }

    public String getFirstShootingRange() {
        return firstShootingRange;
    }

    public void setFirstShootingRange(String firstShootingRange) {
        this.firstShootingRange = firstShootingRange;
    }

    public String getSecondShootingRange() {
        return secondShootingRange;
    }

    public void setSecondShootingRange(String secondShootingRange) {
        this.secondShootingRange = secondShootingRange;
    }

    public String getThirdShootingRange() {
        return thirdShootingRange;
    }

    public void setThirdShootingRange(String thirdShootingRange) {
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
                "31:43", "xxxox", "ooxxx", "xxxxx"));
        SkiBiathlon.allAthletes.add(new BiathlonAthlete(2, "Bianca Prodan", "RO",
                "30:43", "xxxxx", "xoxxx", "xxoxx"));


        try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonResults"))) {
            System.setOut(out);
            for (BiathlonAthlete athletes : SkiBiathlon.allAthletes) {
                System.out.println(athletes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Integer standingCalculation() {

        String[] timeUnits = skiTimeResult.split(":"); //will break the string up into an array
        int minutes = Integer.parseInt(timeUnits[0]); //first element
        int seconds = Integer.parseInt(timeUnits[1]); //second element
        skiTimeResultInSeconds = 60 * minutes + seconds;

        String penaltiesUnits = firstShootingRange + secondShootingRange + thirdShootingRange; // will concatenate the strings
        int counter = 0;
        for (int i = 0; i < penaltiesUnits.length(); i++) {
            if (penaltiesUnits.charAt(i) == 'o') {
                counter++;
            }
        }
        penalties = counter * 10;
        finalSkiTimeResultInSeconds = skiTimeResultInSeconds + penalties;

        return finalSkiTimeResultInSeconds;
    }

    public String reconvertedTime() {
        int reconvertedTime = finalSkiTimeResultInSeconds;
        int min = reconvertedTime / 60;
        int sec = reconvertedTime - (min * 60);
        finalReconvertedTime = String.format("%s:%s", min, sec);
        return finalReconvertedTime;
    }
}
