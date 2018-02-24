package siit.homework;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SkiBiathlonTest {

    @Test
    public void test_copy_between_files() {

        long start = System.nanoTime();


        try (FileInputStream in = new FileInputStream("DataSource" + File.separator + "SkiBiathlonResults");
             FileOutputStream out = new FileOutputStream("DataSource" + File.separator + "SkiBiathlonStandings")) {

            int val;

            while ((val = in.read()) != -1) {
                out.write(val);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.nanoTime() - start);
    }

    @Test

    public void test_file_to_string_and_split() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));

            String stringLine = bufferedReader.readLine();

            while (stringLine != null) {
                System.out.println(stringLine);
                // read next line
                stringLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test

    public void test_file_to_arrayList() {

        List<String> allAthletsList = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("DataSource" + File.separator + "SkiBiathlonStandings"))) {

            //3. convert it into a List
            allAthletsList = stream.collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        //allAthletsList.forEach(System.out::println);

        // for (String list : allAthletsList){
        //   System.out.println(list);

        System.out.println(allAthletsList.get(1));
    }


    @Test
    public void test_BiathlonAthlete_object_transferr_to_file() {
        BiathlonAthlete firstAthelte = new BiathlonAthlete(1, "Sebastian Prodan", "RO",
                new SkiTimeResult(30, 24), "xxxox", "ooxxx", "xxxxx");
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
                new SkiTimeResult(30, 24), "xxxox", "ooxxx", "xxxxx"));
        athletes.add(new BiathlonAthlete(2, "Bianca Prodan", "RO",
                new SkiTimeResult(29, 30), "xxxxx", "xoxxx", "xxoxx"));

        try (PrintStream out = new PrintStream(new File("DataSource" + File.separator + "SkiBiathlonResults"))) {
            System.setOut(out);
            for (BiathlonAthlete allAthletes : athletes) {
                System.out.println(allAthletes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


