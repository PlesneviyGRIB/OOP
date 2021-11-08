import java.util.ArrayList;

/**
 * Class contains an array of objects "subject"
 */
public class Semester {
    private int pointer = 0;
    private int number;
    private ArrayList <Subject> p = new ArrayList<Subject>();

    Semester(int _number) throws Exception{
        if(_number < 1) throw new Exception("Wrong value for semester!");
        number = _number;
    }

    public int getNumber() {
        return number;
    }

    /**
     * Method allows you to store information about a new subject
     */
    public void addSubject(String title, int mark) throws Exception {
        for(int i = 0; i<p.size(); i++) {
            if(title.equals(p.get(i).getTitle())) {
                p.get(i).setMark(mark);
                return;
            }
        }
        p.add(new Subject(title, mark));
    }

    /**
     * Supportive method for average score in Record book
     */
    public int getSumOfMarks() {
        int res = 0;
        for(int i = 0;i < p.size();i++) res += p.get(i).getMark();
        return res;
    }

    /**
     * Supportive method for average score in Record book
     */
    public int getCntOfSub() {
        return p.size();
    }

    /**
     * Method checks if there is a hanged scholarship in the current semester
     */
    public Boolean highScholarship() {
        int cnt = 0;
        for(int i = 0;i < p.size();i++) {
            if(p.get(i).getMark() < 5) {
                if((p.get(i).getMark() == 4) && (cnt == 0)) {
                    cnt++;
                }
                else return false;
            }
        }
        return true;
    }

    /**
     * method for show structure of record book
     */
    public void prn() {
        for(int i = 0; i<p.size(); i++) {
            p.get(i).prn();
        }
        System.out.println();
    }
}