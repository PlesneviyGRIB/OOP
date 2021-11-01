public class MAIN {
    public static void main(String[] args) throws Exception{
        RecordBOOk r = new RecordBOOk("Vasya","Pupkin",230);
        r.setMark(2,"История", 5);
        r.setMark(3,"История", 5);
        r.setMark(4,"Введение в алгебру и анализ", 5);
        r.setMark(5,"История", 5);
        r.setMark(6,"История", 3);
        r.setQualification(5);

        if(r.increased_scholarship()) System.out.println("Yes");
    }
}
