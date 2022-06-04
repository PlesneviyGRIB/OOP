import java.util.*;

public class Generator{
    private static boolean[] resheto = new boolean[10000000];
    private static List<Integer> prost;
    private int count;
    private Random random;
    private List<Integer> list;

    static {
        Arrays.fill(resheto, true);
        for(int i = 2; i< resheto.length / 2;) {
            for (int j = 2; j * i < resheto.length; j++)
                resheto[i * j] = false;
            for(int k = i + 1; k < resheto.length; k++)
                if(resheto[k]){ i = k; break; }
        }
        resheto[0]=false; resheto[1]=false;

        prost = new LinkedList<Integer>();
        for(int i = 0; i< resheto.length;i++)
            if(resheto[i]) prost.add(i);
    }

    Generator(int count){ this.count = count; this.random = new Random(count); list = setList();}

    private List<Integer> setList(){
        List<Integer> res = new LinkedList<Integer>();
        ArrayList<Integer> prost1 = new ArrayList<Integer>(prost);
        for(int i = 0; i< count; i++) res.add(prost1.get(random.nextInt(prost1.size())));
        return res;
    }

    public List<Integer> getList(){ return list; }
}