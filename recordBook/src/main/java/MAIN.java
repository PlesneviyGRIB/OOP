public class MAIN {
    public static void main(String[] args) throws Exception{
        RecordBOOk r = new RecordBOOk("Vasya","Pupkin",230);

        Subjects s = new Subjects();

        for(int i = 1; i<= 8;i++) {
            for(int j =0; j<s.getCount_sub();j++)
                r.setMark(i,s.getSub(j),4);
        }

        r.setMark(8,"История",5);
        r.prn();


    }
}
