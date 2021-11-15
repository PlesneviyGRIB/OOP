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

    public void setText(String _text) {
        text =  _text;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
}