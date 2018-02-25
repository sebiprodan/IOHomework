package siit.homework;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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

    public void test_parse_CVS() {

        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));
             PrintWriter out = new PrintWriter(new FileWriter("DataSource" + File.separator + "SkiBiathlonStandings"))){

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] athlete = line.split(cvsSplitBy);

                System.out.println("Athele: " + athlete[1] + " , result without penalties: " + athlete[3] + "]");
                out.write("\n" +"Athele: " + athlete[1] + " , result without penalties: " + athlete[3] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test

    public void test_file_to_string_and_split() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("DataSource" + File.separator + "SkiBiathlonResults"));

            List<String> lines = new LinkedList<>(); // create a new list
            String line = in.readLine(); // read a line at a time
            while (line != null) {
                lines.add(line);// add the line to your list
                line = in.readLine(); // try to read another line
            }
            System.out.println(lines);
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


