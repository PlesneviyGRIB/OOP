/**
 * title name of subject
 * subSemCnt number of semester for each subject
 * mark measure of knowledge
 */
public class Subject {
    private String title;
    private int mark;

    Subject(String _title, int _mark) throws Exception {
        if((_mark < 2) || (_mark > 5)) throw new Exception("Wrong value of mark!");
        title = _title;
        mark = _mark;
    }

    public int getMark() {
        return mark;
    }

    public String getTitle() {
        return title;
    }

    /**
     * method for show structure of rekord book
     */
    public void prn() {
        System.out.printf("%s %d   ",title,mark);
    }
}