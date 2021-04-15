import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        List<MonitoredData> md ; // = new ArrayList<MonitoredData>();
        Reader rd = new Reader();
        md = rd.read();
        for(MonitoredData m : md){
           System.out.println( m.getDurationperActivity()+ m.getActivityLabel());
        }
        System.out.println(md);
        Control control = new Control(md);
        System.out.println( md.get(0).getStartTime());
        System.out.println( md.get(0).getDayOfStartTime());
        long rez = control.countDistinctDays();
        System.out.println(rez);
        System.out.println(md.get(1).getDurationperActivity());
        Map<String, Long> map;
        map = control.countNoActivities();
        Map<Integer, Map<String, Long>> mapp;
        mapp = control.activitiesPerDay();
        System.out.println(map);
        System.out.println(mapp);
        Writer writer = new Writer(control);
        System.out.println(control.totalTimePerActivity());
        System.out.println(control.totalTimeperActivityM());
        System.out.println(control.task6new());
        writer.write(1);
        writer.write(2);
        writer.write(3);
        writer.write(4);
        writer.write(51);
        writer.write(52);
        writer.write(61);
        writer.write(62);

    }
}
