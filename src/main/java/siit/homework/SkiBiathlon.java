package siit.homework;

import java.io.*;
import java.util.*;

public class SkiBiathlon {

    protected static List<String> arrayListOfStrings;
    protected static Set<BiathlonAthlete> allAthletes;
    protected static String line;

    public static void main(String[] args) {

        allAthletes = new LinkedHashSet<>();

        allAthletes.add(new BiathlonAthlete(1, "Sebastian Prodan", "RO",
                new SkiTimeResult(30, 24), "xxxox", "ooxxx", "xxxxx"));
        allAthletes.add(new BiathlonAthlete(2, "Bianca Prodan", "RO",
                new SkiTimeResult(29, 30), "xxxxx", "xoxxx", "xxoxx"));
        allAthletes.add(new BiathlonAthlete(7, "Gabriela Szabo", "RO",
                new SkiTimeResult(25, 30), "xooox", "xooox", "xooox"));

        try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonResults"))) {
            System.setOut(out);
            for (BiathlonAthlete athlete : allAthletes) {
                System.out.println(athlete);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));

            arrayListOfStrings = new LinkedList<>();
            String line = in.readLine();
            while (line != null) {
                arrayListOfStrings.add(line);
                line = in.readLine();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));
             PrintWriter out = new PrintWriter(new FileWriter("DataSource" + File.separator + "SkiBiathlonStandings"))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] athlete = line.split(cvsSplitBy);

                System.out.println("Athele: " + athlete[1] + " , result without penalties: " + athlete[3] + "]");
                out.write("Athele: " + athlete[1] + " , result without penalties: " + athlete[3] + "\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

