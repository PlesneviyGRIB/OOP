import java.util.ArrayList;

/**
 * Class contains an array of objects "Semester"
 */
public class RecordBook {
    private String name;
    private String lastname;
    private int semCtn;
    private int qualificationWork = 0;
    private ArrayList <Semester> s = new ArrayList<Semester>();

    private ArrayList <String> arrS = new ArrayList<String>();
    private ArrayList <Integer> arrSem = new ArrayList<Integer>();
    private ArrayList <Integer> arrM = new ArrayList<Integer>();

    RecordBook(String _name, String _lastname) {
        name = _name;
        lastname = _lastname;
        semCtn = 0;
    }

    /**
     * Method allows you to add a grade to the grade book
     */
    public void addMark(String title, int mark, int semNumber) throws Exception {
        lastMark(title,mark,semNumber);

        for(int i = 0; i < s.size(); i++) {
            if(s.get(i).getNumber() == semNumber) {
                s.get(i).addSubject(title, mark);
                return;
            }
        }

        Semester tmp = new Semester(semNumber);
        tmp.addSubject(title,mark);
        s.add(tmp);
    }

    /**
     * Supportive private method for check if great diploma is allows
     */
    private void lastMark(String title, int mark, int sem) {
        for(int i =0; i<arrS.size(); i++) {
            if ((arrS.get(i).equals(title)) && (arrSem.get(i) < sem)) {
                arrM.set(i,mark);
                return;
            }
            else {
                if(arrS.get(i).equals(title)) return;
            }
        }
        arrSem.add(sem);
        arrS.add(title);
        arrM.add(mark);
    }

    /**
     * Method returns average score of all marks in record book
     */
    public double averageScore() {
        double sum = 0;
        double cnt = 0;
        for(int i = 0; i<s.size(); i++){
            sum += s.get(i).getSumOfMarks();
            cnt += s.get(i).getCntOfSub();
        }
        if(sum == 0) return sum;
        return sum / cnt;
    }

    /**
     * Supportive private method for check if high scholarship is allows in current semester
     */
    public Boolean isHighScholarship() {
        int tmp = 0;
        for(int i = 0; i<s.size(); i++) {
            if(s.get(i).getNumber()>tmp) tmp = s.get(i).getNumber();
        }
        for(int i = 0; i< s.size();i++){
            if(s.get(i).getNumber() == tmp) return s.get(i).highScholarship();
        }
        return false;
    }

    /**
     * method for show structure of rekord book
     */
    public void prn() {
        for(int i = 0; i<s.size();i++) {
            System.out.printf("|%d|  ",s.get(i).getNumber());
            s.get(i).prn();
        }
        System.out.print("|*|  ");
        for(int i = 0; i<arrS.size(); i++)
            System.out.printf("%s %d   ", arrS.get(i), arrM.get(i));
    }

    public void setQualificationWork(int mark) throws Exception {
        Subject tmp = new Subject("",mark);
        qualificationWork = mark;
    }

    /**
     * Method for check if great diploma is allows
     */
    public Boolean greatDiploma() throws Exception{
        double cnt = 0;
        for(int i = 0; i<arrM.size(); i++) {
            if(arrM.get(i) >= 4) {
                if(arrM.get(i) == 5) cnt++;
            }
            else return false;
        }
        double size = arrM.size();
        if(size * 0.75 <= cnt) {
            if(qualificationWork == 0) throw new Exception("Set qualification work mark!");
            if(qualificationWork == 5) return true;
        }
        return false;
   }
}