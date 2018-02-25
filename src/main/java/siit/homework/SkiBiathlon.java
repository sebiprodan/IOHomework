package siit.homework;

import java.io.*;
import java.util.*;

import static java.util.Collections.addAll;

public class SkiBiathlon {

    protected static List<BiathlonAthlete> biathlonAthleteList;
    protected static Set<BiathlonAthlete> allAthletes;

    public static void main(String[] args) {

        BufferedReader br = null;
        try {
            //Reading the csv file
            br = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));

            //Create List for holding Employee objects
            biathlonAthleteList = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] athleteDetail = line.split(",");

                if (athleteDetail.length > 0) {
                    //Save the employee details in Employee object
                    BiathlonAthlete athlete = new BiathlonAthlete(Integer.parseInt(athleteDetail[0]),
                            athleteDetail[1], athleteDetail[2], athleteDetail[3], athleteDetail[4], athleteDetail[5],
                            athleteDetail[6]);
                    biathlonAthleteList.add(athlete);
                }
            }
            Set<BiathlonAthlete> biathlonAthleteSet = new TreeSet<>(new SkiTimeResultComparator());
            biathlonAthleteSet.addAll(biathlonAthleteList);
//            for (BiathlonAthlete e : biathlonAthleteSet) {
//                System.out.println(e);
            try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonStandings"))) {
                System.setOut(out);
                for (BiathlonAthlete athletes : biathlonAthleteSet) {
                    System.out.println(athletes.getAthleteName() + " " + athletes.getSkiTimeResult() + " " +"("+ athletes.penalties
                            + ")");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//                System.out.println(e.getAthleteNumber() + "   " + e.getAthleteName() + "   "
//                        + e.getCountryCode() + "   " + e.getSkiTimeResult() + "   "
//                        + e.getFirstShootingRange() + "   " + e.getSecondShootingRange() + "   "
//                        + e.getThirdShootingRange());

    } catch(
    Exception ee)

    {
        ee.printStackTrace();
    } finally

    {
        try {
            br.close();
        } catch (IOException ie) {
            System.out.println("Error occurred while closing the BufferedReader");
            ie.printStackTrace();
        }
    }
}
}
