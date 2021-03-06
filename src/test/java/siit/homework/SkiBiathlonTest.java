package siit.homework;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

public class SkiBiathlonTest {

    @Test

    public void test_parse_CVS_without_file() {

        String skiBiathlonResults = "1,Sebastian Prodan,RO,30:01,xxxox,oooxx,oxoxo";
        String[] splitString = skiBiathlonResults.split(","); //will break the string up into an array
        System.out.println(Arrays.toString(splitString));

    }

    @Test

    public void test_parse_CVS() {

        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));
             PrintWriter out = new PrintWriter(new FileWriter("DataSource" + File.separator + "SkiBiathlonStandings"))) {

            List<String> arrayListOfStrings = new LinkedList<>();

            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] athlete = line.split(cvsSplitBy);
                out.write("\n" + "Athele: " + athlete[1] + " , result without penalties: " + athlete[3] + "]");
                arrayListOfStrings.add(athlete[3]);
                System.out.println(arrayListOfStrings);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_parse_CVS_to_Object_Constructor() {

        final String COMMA_DELIMITER = ",";
        BufferedReader br = null;

        try {
            //Reading the csv file
            br = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));

            List<BiathlonAthlete> biathlonAthleteList = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] athleteDetails = line.split(COMMA_DELIMITER);

                if (athleteDetails.length > 0) {
                    BiathlonAthlete athlete = new BiathlonAthlete(Integer.parseInt(athleteDetails[0]),
                            athleteDetails[1], athleteDetails[2], athleteDetails[3], athleteDetails[4], athleteDetails[5],
                            athleteDetails[6]);
                    biathlonAthleteList.add(athlete);
                }
            }

            for (BiathlonAthlete e : biathlonAthleteList) {
                System.out.println(e.getAthleteNumber() + "   " + e.getAthleteName() + "   "
                        + e.getCountryCode() + "   " + e.getSkiTimeResult() + "   "
                        + e.getFirstShootingRange() + "   " + e.getSecondShootingRange() + "   "
                        + e.getThirdShootingRange());
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ie) {
                System.out.println("Error occurred while closing the BufferedReader");
                ie.printStackTrace();
            }
        }
    }


    @Test

    public void time_converter() {

        String skiTimeResult = "30:40";
        String[] units = skiTimeResult.split(":"); //will break the string up into an array
        int minutes = Integer.parseInt(units[0]); //first element
        int seconds = Integer.parseInt(units[1]); //second element
        int duration = 60 * minutes + seconds; //add up our values

        System.out.println(duration);

    }

    @Test

    public void time_converter_to_mmss() {
        int pTime = 1770;
        int min = pTime / 60;
        int sec = pTime - (min * 60);
        String reconvertedTime = String.format("%s:%s", min, sec);

        System.out.println(reconvertedTime);
    }

    @Test

    public void penaltyCalculation() {

        String units = "xxoox" + "oooxx" + "xxxxx";
        int counter = 0;
        for (int i = 0; i < units.length(); i++) {
            if (units.charAt(i) == 'o') {
                counter++;
            }
        }
        int penalties = counter * 10;
        System.out.println(penalties);
    }

    @Test

    public void test_final_standing_calculation() {

        String penaltiesUnits = "oooxx" + "xoxox" + "xxoxo"; // will concatenate the strings
        String skiTimeResult = "29:30";
        String[] timeUnits = skiTimeResult.split(":"); //will break the string up into an array
        int minutes = Integer.parseInt(timeUnits[0]); //first element
        int seconds = Integer.parseInt(timeUnits[1]); //second element
        int skiTimeResultInSeconds = 60 * minutes + seconds;

        int counter = 0;
        for (int i = 0; i < penaltiesUnits.length(); i++) {
            if (penaltiesUnits.charAt(i) == 'o') {
                counter++;
            }
        }
        int penalties = counter * 10;
        int finalSkiTimeResultInSeconds = skiTimeResultInSeconds - penalties;

        System.out.println(finalSkiTimeResultInSeconds);
        ;
    }

    @Test
    public void test_BiathlonAthlete_object_transferr_to_file() {
        BiathlonAthlete firstAthelte = new BiathlonAthlete(1, "Sebastian Prodan", "RO",
                "30:24", "xxxox", "ooxxx", "xxxxx");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DataSource/SkiBiathlonResults"))) {
            out.writeObject(firstAthelte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(firstAthelte);
    }

    @Test
    public void test_BiathlonAthlete_output_transferr_to_file() {

        Set<BiathlonAthlete> athletes = new LinkedHashSet<>();
        athletes.add(new BiathlonAthlete(1, "Sebastian Prodan", "RO",
                "30:24", "xxxox", "ooxxx", "xxxxx"));
        athletes.add(new BiathlonAthlete(2, "Bianca Prodan", "RO",
                "29:30", "xxxxx", "xxxxx", "xxxxx"));
        athletes.add(new BiathlonAthlete(5, "Gabriela Szabo", "HU",
                "29:30", "xxxxx", "xxxxx", "xxoxx"));
        athletes.add(new BiathlonAthlete(7, "Alfonso de Temesvar", "HU",
                "28:00", "ooooo", "ooooo", "xxoxx"));

        try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonResults"))) {
            System.setOut(out);
            for (BiathlonAthlete allAthletes : athletes) {
                System.out.println(allAthletes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void test_comparator() {

        List<BiathlonAthlete> athletes = new ArrayList<>();
        athletes.add(new BiathlonAthlete(1, "Sebastian Papaianii", "RO",
                "30:24", "xxxox", "ooxxx", "xxxox"));
        athletes.add(new BiathlonAthlete(2, "Bianca Prodan", "UK",
                "29:30", "xxxxx", "xoxxx", "xxoxx"));

        for (BiathlonAthlete set1 : athletes) {
            System.out.println(set1);
        }
    }

    @Test
    public void limit_list_n_elements () {

        List<String> athletsList = new ArrayList<>(8);
        athletsList.add("11");
        athletsList.add("Sebi");
        athletsList.add("RO");
        athletsList.add("31:40");
        athletsList.add("xxxox");
        athletsList.add("oooxx");
        athletsList.add("xxxxx");


        List<String> athletsWinners = athletsList.subList(0,3);

        assertEquals(3, athletsWinners.size());

        for (String athletsWinersTest: athletsWinners) {
            System.out.println(athletsWinersTest);

        }

    }


}




