import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.Toolkit;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.*;
public class Med {
    private String name;
    private LocalTime time;
    private ArrayList<DayOfWeek> daysOfWeek;

    private boolean taken;

    private String dosage;

    public Med() {
        this.name = "Not on file";
        this.dosage = "Not on file";
        this.time = null;
        this.daysOfWeek = null;
        this.taken = false;

    }

    public Med(String string){
        this.name = "Tylenol";
        this.dosage = "500mg";
        this.time = LocalTime.parse("00:00");
        this.daysOfWeek = new ArrayList<DayOfWeek>(Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.FRIDAY));
        this.taken = false;

    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setDosage(String dosage) throws FormattingException {
        String validUnits = String.join("|", ValidDosage.mg.name(), ValidDosage.mL.name(), ValidDosage.tsp.name(), ValidDosage.tbsp.name());
        String regex = "\\d+(\\.\\d+)?(" + validUnits + ")";

        // Check if the dosage matches the regex
        if (!Pattern.matches(regex, dosage)) {
            throw new FormattingException();
        }
        this.dosage = dosage;
    }

    public String getDosage() {
        return dosage;
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
                "\n\n\tTaken: " + taken +
                "\nPlease select a medication option:" +
                "\n\t1. Change medication status" +
                "\n\t2. Exit menu";
    }
}