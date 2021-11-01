/**
 *  class record book for student from IT faculty
 */
public class RecordBOOk {
    private String n;
    private String s;
    private int semester = 0;
    private double sumOfMarks = 0;
    private double cntOfMarks = 0;
    private int cntSemesters = 8;
    private Subjects sub = new Subjects();
    private int[][] marks = new int[cntSemesters+1][sub.getCount_sub()+1];
    private int qualification = 0;

    /**
     * @param name name of Student
     * @param surname surname of student
     * @param EGE - exam result (necessary to define if first semester will be high scholarship)
     */
    RecordBOOk(String name, String surname, int EGE) throws Exception{
        n = name;
        s = surname;

        for(int i = 0; i<cntSemesters+1; i++)
            for(int j = 0; j< sub.getCount_sub()+1; j++)
                marks[i][j] = 0;

        if((EGE > 0) && (EGE <= 310))
            marks[cntSemesters][sub.getCount_sub()] = EGE;
        else
        throw new Exception("Inappropriate exam score");
    }

    /**
     * Method for memorizing a score
     * @param SemesterNum number of semester
     * @param subject name of subject
     * @param markValue value in range 2..5
     * @throws Exception throw if some data is wrong
     */
    public void setMark(int SemesterNum, String subject, int markValue) throws Exception {
        if((SemesterNum > 0) && (SemesterNum < 9)) {
            if (sub.indexOfSubject(subject) != -1) {
                if ((markValue > 1) && (markValue < 6)) {
                    marks[SemesterNum-1][sub.indexOfSubject(subject)] = markValue;
                    setLastMark(SemesterNum-1,sub.indexOfSubject(subject), markValue);
                    sumOfMarks += markValue;
                    cntOfMarks++;
                    marks[SemesterNum - 1][sub.getCount_sub()]++;
                    if(SemesterNum > semester) semester = SemesterNum;
                }
                else
                    throw new Exception("Wrong value of mark");
            }
            else
                throw new Exception("Wrong name of course!");
        }
        else
            throw new Exception("Wrong number of semester!");
    }

    public void setMark(int SemesterNum, String subject, String markValue) throws Exception {
        if((SemesterNum > 0) && (SemesterNum < 9)) {
            if (sub.indexOfSubject(subject) != -1) {
                if (markValue.equals("зачет") || markValue.equals("незачет")) {
                    if(markValue.equals("зачет")) {
                        marks[SemesterNum - 1][sub.indexOfSubject(subject)] = 5;
                        sumOfMarks += 5;
                        setLastMark(SemesterNum-1,sub.indexOfSubject(subject), 5);
                    }
                    else {
                        marks[SemesterNum - 1][sub.indexOfSubject(subject)] = 2;
                        sumOfMarks += 2;
                        setLastMark(SemesterNum-1, sub.indexOfSubject(subject), 2);
                    }

                    marks[SemesterNum - 1][sub.getCount_sub()]++;
                    cntOfMarks++;
                    if(SemesterNum > semester) semester = SemesterNum;
                }
                else
                    throw new Exception("Wrong value of mark");
            }
            else
                throw new Exception("Wrong name of course!");
        }
        else
            throw new Exception("Wrong number of semester!");
    }

    public double averageScore() {
        return sumOfMarks / cntOfMarks;
    }

//    public void prn() {
//        for(int i = 0; i<cntSemesters + 1; i++) {
//            for (int j = 0; j < sub.getCount_sub() + 1; j++) {
//                System.out.printf("%d ", marks[i][j]);
//            }
//            System.out.println();
//        }
//    }

    /**
     * Supportive method to set last mark for each subject
     */
    private void setLastMark(int place, int indOfSub, int m) {
        int tmp = 0;
        for(int i = place; i < cntSemesters; i++)
            tmp = marks[i][indOfSub];
        if(tmp == 0) marks[cntSemesters][indOfSub] = m;
    }

    /**
     * Method checks the values in the two-dimensional array for the previous semester and looks for bad grades
     */
    public boolean increased_scholarship(){
        if(semester == 0) {
            if(marks[cntSemesters][sub.getCount_sub()] >= 270) return true;
            return false;
        }
        int cnt = 0;

        for(int i = 0; i<sub.getCount_sub()+1; i++) {
            if (marks[semester - 1][i] == 5) cnt++;
            if (marks[semester -1][i] < 4) return false;
        }

        if((cnt == marks[semester-1][sub.getCount_sub()]) || (cnt + 1 == marks[semester-1][sub.getCount_sub()])) return true;
        return false;
    }

    /**
     * Method check if student could have honors degree
     * @return true/false
     */
    public boolean honorsDegree() {
        double great = 0;
        for(int i = 0; i<sub.getCount_sub(); i++) {
            if (marks[cntSemesters][i] == 5) great++;
            if (marks[cntSemesters][i] == 3) return false;
        }
        if(great >= 0.75 * sub.getCount_sub()) return true;
        return false;
    }

    /**
     * Method set qualification mark and check if value of mark is correct
     */
    public void setQualification(int res) throws Exception{
       if((res>=2) && (res <= 5))
           qualification = res;
       else
           throw new Exception("Inappropriate value for qualifying work!");
    }
}