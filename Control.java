import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Control {
    private List<MonitoredData> monitoredData;

    public Control(List<MonitoredData> list) {
        monitoredData = list;
    }

    public List<MonitoredData> getmonitoredData() {
        return monitoredData;
    }



    public int countDistinctDays() {
        return monitoredData.stream().collect(Collectors.groupingBy((MonitoredData md) -> md.getDayOfStartTime())).size();
        //return monitoredData.stream().filter((MonitoredData m) -> m.getDayOfStartTime()).collect(Collectors.toList()).size();
    }

    public Map<String, Long> countNoActivities() {
        return monitoredData.stream().collect(Collectors.groupingBy(m -> ((MonitoredData) m).getActivityLabel(), Collectors.counting()));
        //return monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData) m).getActivityLabel(), Collectors.counting()));
    }

    public Map<Integer, Map<String, Long>> activitiesPerDay() {
        return monitoredData.stream().collect(Collectors.groupingBy(m -> ((MonitoredData) m).getDayOfStartTime(), (Collectors.groupingBy(m -> ((MonitoredData) m).getActivityLabel(), Collectors.counting()))));
    }
    //--------TASK 5 returning MAP<STRING, LOCALTIME>
    public Map<String, LocalTime> totalTimePerActivity() {
        //return monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getDuration(),Integer::sum ));
        //return monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getDurationperActivity(), MonitoredData::merge ));
        //return monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m ->((MonitoredData)m).getDurationperActivity() , LocalTime:: ()));
        Map<String, Integer> map = monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getDuration(),Integer::sum ));
        Set<String> s = map.keySet();
        Map <String, LocalTime> hm = new HashMap<String, LocalTime>();
        for(String st : s){
            int durata = map.get(st);
            int oraF = durata / 3600;
            int minutF = (durata - oraF * 3600)/60;
            int secundaF = durata- oraF*3600 - minutF*60;
            int ziuaF= 0;
            String oraFS, minutFS, secundaFS, ziuaFS;
            if(oraF < 10){ oraFS ="0" + oraF; }
            else if (oraF > 23){ ziuaF= ziuaF + oraF/24;
             oraFS = "0" + oraF/24 + ""; }
            else { oraFS = oraF + ""; }
            if(minutF < 10){ minutFS ="0" + minutF; }
            else { minutFS = minutF + ""; }
            if(secundaF < 10){ secundaFS ="0" + secundaF; }
            else { secundaFS = secundaF + ""; }
            if(ziuaF < 10){ ziuaFS ="0" + ziuaF; }
            else { ziuaFS ="" + ziuaF; }
            String str = ""+ oraFS + ":" + minutFS + ":" + secundaFS ;
            System.out.println(str);
            LocalTime time = LocalTime.parse(str);
            hm.put(st, time);
        }
        return hm;
    }
    //-----------------------TASK 5 returning MAP<STRING, INTEGER>
    public Map<String, Integer> totalTimeperActivityM(){
        return monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getDurationPerActivityM(),Integer::sum ));
    }
    public  List<String> task6(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map = monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getDurationPerActivityM(),Integer::sum ));
        Map<String, Integer> mapp = new HashMap<String, Integer>();
        mapp = monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getActivityless5(),Integer::sum ));
        Set<String>  str1 = map.keySet();
        Set<String> str2 = mapp.keySet();
        //System.out.println("TOTAL" + " " + map);
        //System.out.println("5 MINUTE: " + mapp);
        List<String> rez = new ArrayList<String>();
        for(String s1: str1){
            for(String s2: str2){
                if(s2.equals(s1)){
                    int in1 = map.get(s1);
                    int in2 = mapp.get(s2);
                    float com = in1 * 90/100;
                    if(in2 > 0 && com < in2){
                       rez.add(s1);
                    }

                }
            }
        }
        return rez;
    }
    //------------------TASK 6 using only stream
    public List<String> task6new(){
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1 = monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getDurationPerActivityM(),Integer::sum ));
        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2 = monitoredData.stream().collect(Collectors.toMap(m -> ((MonitoredData)m).getActivityLabel(), m -> ((MonitoredData)m).getActivityless5(),Integer::sum ));
        Map<String, Integer> finalMap = map2;
        return map1.entrySet().stream().filter(m -> finalMap.get(m.getKey()) / m.getValue() >= 0.9).map(m -> m.getKey()).collect(Collectors.toList());
    }
}