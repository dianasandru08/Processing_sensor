import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Writer {
    public Control control;

    public Writer(Control control) {
        this.control = control;
    }

    public void write(int no) throws IOException {
        String fileName = "Task"+ no +".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        String str = null;
        if(no == 1){
           str ="Task1" + "\n" + "\n";
           for(MonitoredData m: control.getmonitoredData())
           {
               str = str + m + "\n";
           }
        }
        else if(no == 2){
            str = "" + "Task2 " + "\n" +  control.countDistinctDays();
        }
        else if(no == 3){
            str = "" + "Task3 " + "\n" + "\n";
            Map<String, Long> map = new HashMap<String, Long>();
            map =  control.countNoActivities();
            Set<String> set = map.keySet();
            for(String s: set){
                str = str + s + "    " +  map.get(s) + "\n";
            }

        }
        else if(no == 4){

            str = "" + "Task4 " + "\n" + "\n";
            Map<Integer, Map<String, Long>> map1 = new HashMap<Integer, Map<String, Long>>();
            map1 = control.activitiesPerDay();
            Set<Integer> set1 = map1.keySet();
            for(Integer i: set1){
                str = str + i +"    " + map1.get(i) + "\n";
            }
        }
        else if(no == 51){
            str = "" + "Task5 varianta LocalTime " + "\n" + "\n";
            Map<String, LocalTime> map2 = new HashMap<String, LocalTime>();
            map2 = control.totalTimePerActivity();
            Set<String> set2 = map2.keySet();
            for(String ss: set2){
                str = str + ss + "    " + map2.get(ss) + "\n";
            }
        }
        else if(no == 52){
            str = "" + "Task5 varianta Integer " + "\n" + "\n";
            Map<String, Integer> map3 = new HashMap<String, Integer>();
            map3 =  control.totalTimeperActivityM();
            Set<String> set3 = map3.keySet();
            for(String s: set3){
                str = str + s + "    " + map3.get(s) + "\n";
            }
        }
        else if(no == 61){
            str = "" + "Task6 prima varianta" + "\n" + "\n";
            List<String> lista = control.task6();
            str = str + lista;
        }
        else if(no == 62){
            str = "" + "Task6 a doua varianta varianta" + "\n" + "\n";
            List<String> lista1 = control.task6new();
            str = str + lista1;
        }
        writer.write(str);

        writer.close();
    }
}