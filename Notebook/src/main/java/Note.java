import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    private String title;
    private Date date;
    private String text;

    Note(String _title, String _text) {
        title = _title;
        text = _text;
        date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() { return date; }

    public void pr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        System.out.printf("[%S] <%s> %s\n",simpleDateFormat.format(date), title, text);
    }
}