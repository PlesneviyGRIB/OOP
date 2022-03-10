import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class NoteBook {
    private ArrayList<Note> notes;
    private Gson gson;
    private File f;

    NoteBook(File _f) throws FileNotFoundException {
        f = _f;
        notes = new ArrayList<Note>();
        gson = new GsonBuilder().create();
        notes = gson.fromJson(new FileReader(f.getAbsolutePath()), new TypeToken<ArrayList<Note>>(){}.getType());
    }

    public void addNote(String title, String text) {
        Note note = new Note(title,text);
        notes.add(note);
    }

    public void rmNote(String title) {
        if(title.equals("*")) {
            notes.clear();
            return;
        }
        for(int i = 0; i < notes.size(); i++)
            if(notes.get(i).getTitle().equals(title)) {
                notes.remove(i);
                return;
            }
    }

    public void myExit() throws IOException {
        FileWriter write = new FileWriter(f.getAbsolutePath());
        gson.toJson(notes, write);
        write.close();
    }

    public void pr() {
        for(int i = 0; i < notes.size(); i++)
            notes.get(i).pr();
    }

    public void pr(Param param) {
        ArrayList<String> words = param.getWords();
        int ind = 0;

        for(int i = 0; i < notes.size(); i++) {
            if((notes.get(i).getDate().after(param.getDate0())) && (notes.get(i).getDate().before(param.getDate1()))) {
                if(words.size() == 0) ind = 1;
                for (int j = 0; j < words.size(); j++) {
                    if ((notes.get(i).getTitle().contains(words.get(j)))) {
                        ind = 1;
                        break;
                    }
                }
                if(ind == 1) notes.get(i).pr();
                ind = 0;
            }
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}