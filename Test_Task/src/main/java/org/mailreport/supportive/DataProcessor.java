package org.mailreport.supportive;

import lombok.AccessLevel;
import lombok.Getter;
import org.mailreport.Dao;

import java.util.*;

@Getter
public class DataProcessor {
    @Getter(AccessLevel.NONE)
    private Dao dao;

    private List<String> removedURLs;
    private List<String> newURLs;
    private List<String> changedURLs;

    public DataProcessor(Dao dao){
        this.dao = dao;
        process();
    }

    private void process(){
        Set<String> removed = removed(dao);
        removedURLs = new ArrayList<>(removed);

        Set<String> added = added(dao);
        newURLs = new ArrayList<>(added);

        Set<String> changed = changed(dao, removed);
        changedURLs = new ArrayList<>(changed);
    }

    private Set<String> removed(Dao dao){
        Set<String> previous = dao.getPrevious().keySet();
        Set<String> current = dao.getCurrent().keySet();

        previous.removeAll(current);
        return previous;
    }

    private Set<String> added(Dao dao){
        Set<String> previous = dao.getPrevious().keySet();
        Set<String> current = dao.getCurrent().keySet();

        current.removeAll(previous);
        return current;
    }

    private Set<String> changed(Dao dao, Set<String> removed){
        HashMap<String, String> previous = dao.getPrevious();
        HashMap<String, String> current = dao.getCurrent();

        Set<String> prev = dao.getPrevious().keySet();
        prev.removeAll(removed);

        Set<String> changed = new HashSet<>();

        prev.forEach(key -> {
            if(!previous.get(key).equals(current.get(key))) changed.add(key);
        });

        return changed;
    }
}
