package beans;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeBean {
    public String getTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
