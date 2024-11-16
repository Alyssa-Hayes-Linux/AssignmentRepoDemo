import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
public class Med {
    private String name;
    private LocalTime time;
    private ArrayList<DayOfWeek> daysOfWeek;

    private boolean taken;

    private String dosage;

    public Med() {
        this.name = "Not on file";
        this.time = null;
        this.daysOfWeek = null;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
    public LocalTime getTime() {
        return this.time;
    }
    public void setDaysOfWeek(ArrayList<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
    public ArrayList<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String daysOfWeekToString(){
        StringBuilder toReturn = new StringBuilder();
        for (DayOfWeek dayOfWeek : daysOfWeek) {
            toReturn.append("\n\t\t").append(dayOfWeek);
        }
        return toReturn.toString();
    }


    //this basically is going to display all the med infomration based off what's "selected" in medMenu
    //might have to add dosage information
    //also going to make an option so it can change the "taken" value
    //(Medication name):
    //  Dosage:
    //  Days Taken:
    //   list the days
    //  Time taken:
    //
    //  Taken: (true/false)
    //press 1 to change status
    //press 2 to go back/exit

    /**
     *
     * @return a String containing medication information
     */
    public String medDetails(){
        return this.name + ":" +
                "\n\tDosage: " + dosage +
                "\n\tDays Taken: " + daysOfWeekToString() +
                "\n\tTime Taken: " + time.toString() +
                "\n\n\tTaken: " + taken;
    }
}