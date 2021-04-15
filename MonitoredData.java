import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class MonitoredData {
    private String startTime;
    private String endTime;
    private String activityLabel;

    public MonitoredData(String startTime, String endTime, String activityLabel) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityLabel = activityLabel;
    }

    public String getStartTime() {
        return startTime;
    }
    public int getDayOfStartTime(){
        String s = this.getStartTime();
        String[] parts = s.split(" ");
        String[] parts1 = parts[0].split("-");
        String ziuaS = parts1[2];
        String lunaS = parts1[1];
        String fin = "" + ziuaS + lunaS; //pentru a nu considera ziua cu acelasi numar din doua luni diferite o aceeasi zi, am codificat ziua in functie de luna prin concatenarea lunii
        int finI = Integer.parseInt(fin);
        return finI;
    }

    public LocalTime getDurationperActivity(){
        String start = this.getStartTime();
        String[] splitstart = start.split(" ");
        String[] splitstart1 = splitstart[1].split(":");
        int oraS = Integer.parseInt(splitstart1[0]);
        int minutS = Integer.parseInt(splitstart1[1]);
        int secundaS = Integer.parseInt(splitstart1[2]);
        String end = this.getEndTime();
        String[] splitend = end.split(" ");
        String[] splitend1 = splitend[1].split(":");
        int oraE = Integer.parseInt(splitend1[0]);
        int minutE = Integer.parseInt(splitend1[1]);
        int secundaE = Integer.parseInt(splitend1[2]);
        if(oraS > oraE){ oraE = oraE + 24; }
        int durata = (oraE -oraS) * 3600 + (minutE- minutS) * 60 + (secundaE- secundaS);
        int oraF = durata / 3600;
        int minutF = (durata - oraF * 3600)/60;
        int secundaF = durata- oraF*3600 - minutF*60;
        String oraFS, minutFS, secundaFS;
        if(oraF < 10){ oraFS ="0" + oraF; }
        else { oraFS = oraF + ""; }
        if(minutF < 10){
            minutFS ="0" + minutF; }
        else { minutFS = minutF + ""; }
        if(secundaF < 10){
            secundaFS ="0" + secundaF; }
        else { secundaFS = secundaF + ""; }
        String str = oraFS + ":" + minutFS + ":" + secundaFS ;
        LocalTime time = LocalTime.parse(str);
        return time;
    }
    public int getActivityless5()
    {
        boolean rez;
        int dur = 0;
        if(this.getDurationPerActivityM() < 5){
            rez = true;
            dur = this.getDurationPerActivityM();
        }
        else{
            dur = 0;
            rez = false;
        }
        return dur;
    }
    public int getDurationPerActivityM(){
        int rez = this.getDuration() / 60;
        return rez;
    }
    public LocalTime merge(MonitoredData md1, MonitoredData md2){
        LocalTime t1 = md1.getDurationperActivity();
        LocalTime t2 = md2.getDurationperActivity();
        int ora1 = t1.getHour();
        int ora2 = t2.getHour();
        int minut1 = t1.getMinute();
        int minut2 = t2.getMinute();
        int secunda1 = t1.getSecond();
        int secunda2 = t2.getSecond();
        int oraf =0;
        int minutf =0;
        int secundaf = secunda1 + secunda2;
        if(secundaf > 60){
            minutf = minutf + secundaf / 60;
        }
        minutf =minutf+ minut1 + minut2;
        if(minutf > 60){
            oraf = oraf + 1;
        }
        oraf = oraf + ora1 + ora2;
        String oraFS, minutFS, secundaFS;
        if(oraf < 10){
            oraFS ="0" + oraf;
        }
        else {
            oraFS = oraf + "";
        }
        if(minutf < 10){
            minutFS ="0" + minutf;
        }
        else {
            minutFS = minutf + "";
        }
        if(secundaf < 10){
            secundaFS ="0" + secundaf;
        }
        else {
            secundaFS = secundaf + "";
        }
        String str = oraFS + ":" + minutFS + ":" + secundaFS ;
        LocalTime time = LocalTime.parse(str);
        return time;

    }
    public int getMonthOfStartTime(){
        String s = this.getStartTime();
        String[] parts = s.split(" ");
        String[] parts1 = parts[0].split("-");
        String lunaS = parts1[1];
        int lunaI = Integer.parseInt(lunaS);
        return lunaI;
    }

    public int getDuration(){
        String start = this.getStartTime();
        String[] splitstart = start.split(" ");
        String[] splitstart1 = splitstart[1].split(":");
        int oraS = Integer.parseInt(splitstart1[0]);
        int minutS = Integer.parseInt(splitstart1[1]);
        int secundaS = Integer.parseInt(splitstart1[2]);

        String end = this.getEndTime();
        String[] splitend = end.split(" ");
        String[] splitend1 = splitend[1].split(":");
        int oraE = Integer.parseInt(splitend1[0]);
        int minutE = Integer.parseInt(splitend1[1]);
        int secundaE = Integer.parseInt(splitend1[2]);
        if(oraS > oraE){
            oraE = oraE + 24;
        }
        int durata = (oraE -oraS) * 3600 + (minutE- minutS) * 60 + (secundaE- secundaS);
        return durata;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }


    @Override
    public String toString() {
       String rez ="";
       rez = rez + "("+startTime +", " + endTime + ", " + activityLabel + ")";
       return rez;
    }
}
