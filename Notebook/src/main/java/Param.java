import java.util.ArrayList;
import java.util.Date;

public class Param {
    private Date date0;
    private Date date1;
    private ArrayList<String> words;

    Param (Date _date0, Date _date1)
    {
        date0 = _date0;
        date1 = _date1;
        words = new ArrayList<String>();
    }

    public Date getDate0() {
        return date0;
    }

    public Date getDate1() {
        return date1;
    }

    public void addWord(String word) {
        words.add(word);
    }

    public ArrayList<String> getWords() {
        return words;
    }
}