package siit.homework;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.Set;

public class SkiBiathlon {

    public static void main(String[] args) {

        Set<BiathlonAthlete> allAthletes = new LinkedHashSet<>();

        allAthletes.add(new BiathlonAthlete(1, "Sebastian Prodan", "RO",
                new SkiTimeResult(30, 24), "xxxox", "ooxxx", "xxxxx"));
        allAthletes.add(new BiathlonAthlete(2, "Bianca Prodan", "RO",
                new SkiTimeResult(29, 30), "xxxxx", "xoxxx", "xxoxx"));


        try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonResults"))) {
            System.setOut(out);
            for (BiathlonAthlete athletes : allAthletes) {
                System.out.println(athletes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

