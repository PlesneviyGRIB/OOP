package my.snake;

import java.util.ArrayList;
import java.util.List;

public class AllLets implements Changeable{
    private static AllLets allLets = new AllLets();

    private List<Let> lets = new ArrayList<>();

    private AllLets(){};

    static AllLets getInstance(){
        return allLets;
    }

    void addLet(Let let){
        lets.add(let);
    }

    void rmLet(Let let){
        lets.remove(let);
    }

    List<Let> getLets(){
        return lets;
    }

    @Override
    public void change() {
        ArrayList<Let> newLets = new ArrayList<>(lets);

        for(Let let: lets)
            if((let.getClass().equals(Snakes.Snake.class) && !((Snakes.Snake)let).isAlive())) newLets.remove(let);

        lets = newLets;
    }

    void newObject(){ allLets = new AllLets(); }
}
