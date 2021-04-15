import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    public static List<MonitoredData> read(){


        List<MonitoredData> monitoredData =  new ArrayList<MonitoredData>();
        try{
            monitoredData = Files.lines(Paths.get("C:\\fisiereText\\Activities.txt"))
                    .map(x-> x.split("\t\t"))
                    //.filter(x-> x.length == 3)
                    .map(arr -> new MonitoredData(arr[0], arr[1], arr[2]))
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return monitoredData;
        /*
        //String fileName = "Activities.txt";
        String fileName = "C:\\fisiereText\\Activities.txt";
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();
        //list.forEach(System.out::println);
        for(String s: list){
            System.out.println(s);
            String[] parts = s.split("		");
            monitoredData.add(new MonitoredData(parts[0], parts[1], parts[2]));
        }

        return monitoredData;


        FileReader fr = null;
        try {
            fr = new FileReader("C:\\fisiereText\\Activities.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<MonitoredData> monitoredData = new ArrayList<MonitoredData>();

        while(line != null) {
            String s[] = line.split("		");
            monitoredData.add(new MonitoredData(s[0], s[1], s[2]));
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return monitoredData;

         */

    }
}
