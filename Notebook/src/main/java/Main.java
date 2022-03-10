import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        String path = "/home/egor/GitHub/OOP/Notebook/data";
        File f = new File(path);
    	f.mkdir();

	    path  = "/home/egor/GitHub/OOP/Notebook/data/Notebook.json";
	    f = new File(path);
        if(f.createNewFile()) {
            FileWriter write = new FileWriter(f.getAbsolutePath());
            write.write("[]");
            write.close();
        }

        Parser parser = new Parser(args);
        NoteBook noteBook = new NoteBook(f);

        Date date0 = new Date();
        Date date1 = date0;

        int r = parser.ind();
        while(r != -1) {
            if(args[r].equals("-add")) noteBook.addNote(args[++r],args[++r]);
            if(args[r].equals("-rm")) noteBook.rmNote(args[r + 1]);
            if(args[r].equals("-show")) {
                Param param;
                param = parser.isParamShow(r);
                if (param == null) noteBook.pr();
                else noteBook.pr(param);
            }
            r = parser.ind();
        }
        noteBook.myExit();
    }
}