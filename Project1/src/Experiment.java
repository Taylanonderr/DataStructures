public class Experiment {
    public String setup ;
    public int day;
    public String time;
    public boolean completed;
    public float accuracy;

    public Experiment() {

    }

    public String getSetup(){
        return setup;
    }
    public int getDay(){
        return day;
    }
    public String getTime(){
        return time;
    }
    public boolean getCompleted(){
        return completed;
    }
    public float getAccuracy(){
        return accuracy;
    }
    public void setSetup(String Setup){
        setup=Setup;
    }
    public void setDay(int Day){
        day=Day;
    }
    public void setTime(String Time){
        time=Time;
    }
    public void setCompleted(boolean Completed){
        completed=Completed;
    }
    public void setAccuracy(float Accuracy){
        accuracy=Accuracy;
    }
    public Experiment(String Setup,String Time,boolean okey_or_not,int Day,float completed_rate) {
        day=Day;
        setup=Setup;
        time=Time;
        completed=okey_or_not;
        accuracy=completed_rate;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "setup='" + setup + '\'' +
                ", day=" + day +
                ", time='" + time + '\'' +
                ", accuracy=" + accuracy +
                ", completed=" + completed +
                '}';
    }


}