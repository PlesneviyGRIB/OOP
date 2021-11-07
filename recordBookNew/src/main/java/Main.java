public class Main {
    public static void main(String[] arg) throws Exception {
        RecordBook r = new RecordBook("Саша", "Пупкин");

        r.addMark("Math",5,1);
        r.addMark("Math",3,3);
        r.addMark("Math",3,4);
        r.addMark("History",5,3);
        r.addMark("Math",2,3);
        r.addMark("Math",3,3);
        r.addMark("PE",5,4);
        r.addMark("PE",5,4);
        r.addMark("PE",5,4);
        r.addMark("PE",3,4);
        r.addMark("Geometry",4,5);
        r.addMark("Math",5,5);
        r.addMark("History",3,1);
        r.addMark("PE",2,7);
        r.setQualificationWork(5);
        r.prn();

        System.out.printf("\n%f \n", r.averageScore());
        System.out.println(r.isHighScholarship());
        System.out.println(r.greatDiploma());
    }
}