import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNotebook {
    @Test
    public void NormalTest() throws Exception {
        String path = "/home/egor/GitHub/OOP/Notebook/data";
        File f = new File(path);
        f.mkdir();
        path  = "/home/egor/GitHub/OOP/Notebook/data/Notebook.json";
        f = new File(path);

        NoteBook noteBook = new NoteBook(f);

        ArrayList<Note> notesTest = noteBook.getNotes();
        Note note = new Note("Test","My test for notebook");
        notesTest.add(note);
        note = new Note("Test1","My test 1 for notebook");
        notesTest.add(note);
        note = new Note("Test","My test for notebook");
        notesTest.remove(note);


        noteBook.addNote("Test","My test for notebook");
        noteBook.addNote("Test1","My test 1 for notebook");
        noteBook.rmNote("Test");

        for(int i = 0; i < notesTest.size(); i++) {
            ArrayList<Note> noteBookNotes = noteBook.getNotes();
            assertEquals(notesTest.get(i), noteBookNotes.get(i));
        }
    }
}