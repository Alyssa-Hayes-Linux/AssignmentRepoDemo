import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
public class Med {
    private String name;
    private LocalTime time;
    private ArrayList<DayOfWeek> daysOfWeek;
    public void Med() {

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
}
